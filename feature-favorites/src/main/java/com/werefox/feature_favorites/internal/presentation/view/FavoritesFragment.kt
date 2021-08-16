package com.werefox.feature_favorites.internal.presentation.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.werefox.core_domain.entity.CatFavoriteEntity
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_presentation.fragment.MvpBaseFragment
import com.werefox.feature_favorites.R
import com.werefox.feature_favorites.di.FavoritesComponentProvider
import com.werefox.feature_favorites.internal.presentation.presenter.FavoritesPresenter
import kotlinx.android.synthetic.main.fragment_favorites.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class FavoritesFragment : MvpBaseFragment(), FavoritesView {

    @Inject
    internal lateinit var presenterProvider: Provider<FavoritesPresenter>

    @InjectPresenter
    internal lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    internal fun providePresenter() = presenterProvider.get()

    @Inject
    internal lateinit var resourceManager: ResourceManager

    private lateinit var favoriteItemAdapter: FavoriteItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as FavoritesComponentProvider)
            .provideFavoritesComponent()
            .inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    private fun initUI(view: View) {
        initToolbar(view)
        favoriteItemAdapter = FavoriteItemAdapter(resourceManager)
        val recyclerCats = view.findViewById<RecyclerView>(R.id.recycler_favorites_cats)
        recyclerCats.adapter = favoriteItemAdapter
        recyclerCats.layoutManager = LinearLayoutManager(requireContext())
        val pb = view.findViewById<ProgressBar>(R.id.progress_favorites)
    }

    private fun initToolbar(view: View) {
        setupToolbar(
            view,
            R.string.fragment_favorites_toolbar_title,
            null,
            R.drawable.ic_arrow_back,
            false,
            backPressedListener
        )
    }

    companion object {
        internal fun newInstance(): FavoritesFragment {
            val fragment = FavoritesFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }

    //region ==================== UI handlers ====================

    private val backPressedListener = View.OnClickListener { presenter.onBackPressed() }

    //endregion

    override fun showFavoriteImages(favorites: List<CatFavoriteEntity>) {
        favoriteItemAdapter.update(favorites)
    }

    override fun hideLoader() {
        progress_favorites.visibility = View.GONE
    }
}