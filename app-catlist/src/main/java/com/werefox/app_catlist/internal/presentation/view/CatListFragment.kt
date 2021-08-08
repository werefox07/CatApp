package com.werefox.app_catlist.internal.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.werefox.app_catlist.R
import com.werefox.app_catlist.di.CatListPresenterComponent
import com.werefox.app_catlist.di.CatListPresenterComponentProvider
import com.werefox.app_catlist.internal.presentation.presenter.CatListPresenter
import com.werefox.core_domain.manager.ComponentOwner
import com.werefox.core_presentation.fragment.MvpBaseFragment

class CatListFragment : MvpBaseFragment(), CatListView, ComponentOwner<CatListPresenterComponent> {

    @InjectPresenter
    internal lateinit var presenter: CatListPresenter

    override fun addToFavorite() {
        presenter.addToFavorite()
    }

    override fun saveImage() {
        presenter.saveImage()
    }

    override fun provideComponent(): CatListPresenterComponent {
        return CatListPresenterComponentProvider.getInstance().component
    }

    override fun inject(t: CatListPresenterComponent) = t.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catlist, container, false)
    }

    companion object {
        internal fun newInstance(): CatListFragment {
            val fragment = CatListFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}