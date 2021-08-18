package com.werefox.catapp.app.internal.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.werefox.catapp.R
import com.werefox.catapp.app.AppContext
import com.werefox.catapp.app.di.DaggerMainContainerComponent
import com.werefox.catapp.app.di.MainContainerComponent
import com.werefox.catapp.app.di.MainContainerModule
import com.werefox.catapp.app.internal.presentation.presenter.MainContainerPresenter
import com.werefox.core_domain.di.ComponentOwner
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
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

    @Inject
    internal lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        AppNavigator(this, R.id.fragment_root_container)
    }

    //region ===================== Lifecycle ======================

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        inject(provideComponent())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    //endregion

    //region ===================== DI ======================

    override fun provideComponent(): MainContainerComponent {
        return DaggerMainContainerComponent.builder()
            .mainContainerModule(MainContainerModule())
            .mainContainerDependencies((application as AppContext).appComponent())
            .build()
    }

    override fun inject(t: MainContainerComponent) = t.inject(this)

    //endregion
}