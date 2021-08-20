package com.werefox.feature_catlist.internal.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_presentation.fragment.MvpBaseFragment
import com.werefox.feature_catlist.R
import com.werefox.feature_catlist.di.CatListComponentProvider
import com.werefox.feature_catlist.internal.presentation.presenter.CatListPresenter
import kotlinx.android.synthetic.main.fragment_catlist.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider


class CatListFragment : MvpBaseFragment(), CatListView, CatItemActionListener {

    @Inject
    internal lateinit var presenterProvider: Provider<CatListPresenter>

    @InjectPresenter
    internal lateinit var presenter: CatListPresenter

    @ProvidePresenter
    internal fun providePresenter() = presenterProvider.get()

    @Inject
    internal lateinit var resourceManager: ResourceManager

    @Inject
    internal lateinit var picasso: Picasso

    private lateinit var catItemAdapter: CatItemAdapter

    private var loadInProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as CatListComponentProvider)
            .provideCatListComponent()
            .inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_catlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    private fun initUI(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        initToolbar(view)
        toolbar.inflateMenu(R.menu.menu_cat_list)
        catItemAdapter = CatItemAdapter(this, resourceManager, picasso)
        recycler_cats.adapter = catItemAdapter
        recycler_cats.layoutManager = LinearLayoutManager(requireContext())
        recycler_cats.addOnScrollListener(recyclerViewOnScrollListener)
    }

    private fun initToolbar(view: View) {
        setupToolbar(
            view,
            R.string.fragment_catlist_toolbar_title,
            null,
            null,
            false,
            null
        )
    }
    //region ==================== Options menu ====================

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_cat_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_menu_favorites) {
            presenter.onFavoritesClick()
        }
        return true
    }
    //endregion

    companion object {
        internal fun newInstance(): CatListFragment {
            val fragment = CatListFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
    //region ==================== UI event ====================

    override fun onClickDownloadImage(drawable: Drawable?, id: String) {
        drawable?.let {
            presenter.saveImageToExternalStorage(it, id)
        } ?: run {
            showToast(resourceManager.getString(R.string.cat_item_image_not_load))
        }
    }

    override fun onClickAddToFavorite(cat: CatEntity, title: String) {
        presenter.addToFavorite(cat, title)
    }

    //endregion

    //region ==================== UI handlers ====================

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val itemCount = catItemAdapter.itemCount

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && itemCount > 0 && !loadInProgress) {
                loadInProgress = true
                presenter.onScrolledToBottom()
            }
        }
    }
    //endregion

    override fun showImages(catEntities: List<CatEntity>) {
        catItemAdapter.update(catEntities)
    }

    override fun addImages(catEntities: List<CatEntity>) {
        catItemAdapter.add(catEntities)
    }

    override fun setLoaderVisibility(visibility: Boolean) {
        if (visibility) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    override fun setLoadInProgress(value: Boolean) {
        loadInProgress = value
    }

    override fun showToast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }
}