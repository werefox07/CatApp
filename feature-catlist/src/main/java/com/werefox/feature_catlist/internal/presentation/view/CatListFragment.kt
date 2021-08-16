package com.werefox.feature_catlist.internal.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.werefox.feature_catlist.di.CatListComponentProvider
import com.werefox.feature_catlist.internal.presentation.presenter.CatListPresenter
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_presentation.fragment.MvpBaseFragment
import kotlinx.android.synthetic.main.fragment_catlist.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider
import android.view.*
import com.werefox.feature_catlist.R


class CatListFragment : MvpBaseFragment(), CatListView, CatItemActionListener {

    @Inject
    internal lateinit var presenterProvider: Provider<CatListPresenter>

    @InjectPresenter
    internal lateinit var presenter: CatListPresenter

    @ProvidePresenter
    internal fun providePresenter() = presenterProvider.get()

    @Inject
    internal lateinit var resourceManager: ResourceManager

    private lateinit var catItemAdapter: CatItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as CatListComponentProvider)
            .provideCatListComponent()
            .inject(this)
        super.onCreate(savedInstanceState)
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
        initToolbar(view)
        catItemAdapter = CatItemAdapter(this, resourceManager)
        val recyclerCats = view.findViewById<RecyclerView>(R.id.recycler_cats)
        recyclerCats.adapter = catItemAdapter
        recyclerCats.layoutManager = LinearLayoutManager(requireContext())
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cat_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionMenuFavorites) {
            presenter.onFavoritesClick()
        }
        return true
    }

    companion object {
        internal fun newInstance(): CatListFragment {
            val fragment = CatListFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
    //region ==================== UI event ====================

    override fun onClickSave(drawable: Drawable, id: String) {
        presenter.saveImageToExternalStorage(drawable, id)
    }

    override fun onClickAddToFavorite(cat: CatEntity, title: String) {
        presenter.addToFavorite(cat, title)
    }

    //endregion

    override fun showImages(catEntities: List<CatEntity>) {
        catItemAdapter.update(catEntities)
    }

    override fun hideLoader() {
        progress.visibility = View.GONE
    }

    override fun showToast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }
}