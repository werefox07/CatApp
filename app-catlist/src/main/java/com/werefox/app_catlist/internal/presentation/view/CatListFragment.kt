package com.werefox.app_catlist.internal.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.werefox.app_catlist.R
import com.werefox.app_catlist.di.CatListPresenterComponent
import com.werefox.app_catlist.di.CatListPresenterModule
import com.werefox.app_catlist.di.DaggerCatListPresenterComponent
import com.werefox.core_domain.entity.Cat
import com.werefox.app_catlist.internal.presentation.presenter.CatListPresenter
import com.werefox.core_domain.manager.ComponentOwner
import com.werefox.core_presentation.fragment.MvpBaseFragment
import javax.inject.Inject
import javax.inject.Provider

class CatListFragment : MvpBaseFragment(), CatListView, ComponentOwner<CatListPresenterComponent> {

    @InjectPresenter
    internal lateinit var presenter: CatListPresenter

    @Inject
    internal lateinit var presenterProvider: Provider<CatListPresenter>

    @ProvidePresenter
    internal fun provideCatListPresenter() = presenterProvider.get()

    private val catItemAdapter = CatItemAdapter()

    override fun provideComponent(): CatListPresenterComponent {
        return DaggerCatListPresenterComponent.builder()
            .catListPresenterModule(CatListPresenterModule())
            .catListDependencies((activity?.application as AppContext).provideComponent())
            .build()
    }

    override fun inject(t: CatListPresenterComponent) = t.inject(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        inject(provideComponent())
        super.onCreate(savedInstanceState)
        presenter.loadImage()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    private fun initUI(itemView: View) {
        val recyclerCats = itemView.findViewById<RecyclerView>(R.id.recycler_cats)
        recyclerCats.adapter = catItemAdapter
    }

    companion object {
        internal fun newInstance(): CatListFragment {
            val fragment = CatListFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }

    override fun showImages(cats: List<Cat>) {
        catItemAdapter.update(cats)
    }
}