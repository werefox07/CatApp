package com.werefox.catapp.app

import android.app.Application
import com.werefox.feature_catlist.di.CatListComponent
import com.werefox.feature_catlist.di.CatListComponentProvider
import com.werefox.core_data.network.CatsApi
import com.werefox.feature_favorites.di.FavoritesComponent
import com.werefox.feature_favorites.di.FavoritesComponentProvider

class AppContext : Application(), LifeCycleHandlerRegistrartor, CatListComponentProvider,
    FavoritesComponentProvider {

    private lateinit var applicationComponent: ApplicationComponent

    override fun registerLifeCycleHandler(callback: ActivityLifecycleCallbacks) {
        registerActivityLifecycleCallbacks(callback)
    }

    fun getAppComponent(): ApplicationComponent {
        return applicationComponent
    }

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()
        super.onCreate()
    }

    override fun provideCatListComponent(): CatListComponent = getAppComponent().catListComponent()

    override fun provideFavoritesComponent(): FavoritesComponent = getAppComponent().favoritesComponent()
}