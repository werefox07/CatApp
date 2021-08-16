package com.werefox.catapp.app

import android.app.Application
import com.werefox.feature_catlist.di.CatListComponent
import com.werefox.feature_catlist.di.CatListComponentProvider
import com.werefox.core_data.network.CatsApi
import com.werefox.feature_favorites.di.FavoritesComponent
import com.werefox.feature_favorites.di.FavoritesComponentProvider

class AppContext : Application(), LifeCycleHandlerRegistrartor, CatListComponentProvider,
    FavoritesComponentProvider {

    override fun registerLifeCycleHandler(callback: ActivityLifecycleCallbacks) {
        registerActivityLifecycleCallbacks(callback)
    }

    fun appComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        CatsApi.initService()
    }

    override fun provideCatListComponent(): CatListComponent = appComponent().catListComponent()

    override fun provideFavoritesComponent(): FavoritesComponent = appComponent().favoritesComponent()
}