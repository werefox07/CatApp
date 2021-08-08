package com.werefox.catapp.app.internal.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.werefox.catapp.R
import com.werefox.catapp.app.di.MainContainerComponent
import com.werefox.catapp.app.di.MainContainerComponentProvider
import com.werefox.catapp.app.internal.presentation.presenter.MainContainerPresenter
import com.werefox.core_domain.manager.ComponentOwner
import javax.inject.Inject
import javax.inject.Provider


class MainContainerActivity : MvpAppCompatActivity(), MainContainerView,
    ComponentOwner<MainContainerComponent> {

    @Inject
    internal lateinit var presenterProvider: Provider<MainContainerPresenter>

    @InjectPresenter
    internal lateinit var presenter: MainContainerPresenter

    @ProvidePresenter
    internal fun providePresenter(): MainContainerPresenter = presenterProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun provideComponent(): MainContainerComponent {
        return MainContainerComponentProvider.newInstance().mainContainerComponent
    }

    override fun inject(t: MainContainerComponent) = t.inject(this)
}