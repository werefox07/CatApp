package com.werefox.catapp.app

import android.app.Application
import com.werefox.app_catlist.di.CatListComponent
import com.werefox.app_catlist.di.CatListComponentProvider
import com.werefox.core_data.network.CatsApi

class AppContext : Application(), LifeCycleHandlerRegistrartor, CatListComponentProvider {

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

}