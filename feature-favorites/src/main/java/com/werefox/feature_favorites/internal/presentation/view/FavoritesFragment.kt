package com.werefox.feature_favorites.internal.presentation.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
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

    @Inject
    internal lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.fragment_root_container)
    }

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

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)

    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    //region ==================== UI handlers ====================

    private val backPressedListener = View.OnClickListener { presenter.onBackPressed() }

    //endregion

    override fun showFavoriteImages(favorites: List<CatFavoriteEntity>) {
        favoriteItemAdapter.update(favorites)
    }

    override fun setVisibilityLoader(visibility: Boolean) {
        if (visibility) {
            progress_favorites.visibility = View.VISIBLE

        } else {
            progress_favorites.visibility = View.GONE
        }
    }

    override fun setVisibilityStub(visibility: Boolean) {
        if (visibility) {
            tv_stub.visibility = View.VISIBLE

        } else {
            tv_stub.visibility = View.GONE
        }
    }
}