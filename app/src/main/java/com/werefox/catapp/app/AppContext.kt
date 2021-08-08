package com.werefox.catapp.app

import android.app.Application
import com.werefox.core_domain.manager.ComponentOwner

class AppContext : Application(),
    LifeCycleHandlerRegistrartor,
    ComponentOwner<ApplicationComponent> {

    private val dependencyInitializer: DependencyInitializer =
        DependencyInitializer(this)

    override fun registerLifeCycleHandler(callback: ActivityLifecycleCallbacks) {
        registerActivityLifecycleCallbacks(callback)
    }

    override fun provideComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(
                    ApplicationModule(
                        this
                    )
                )
                .build()
    }

    override fun inject(t: ApplicationComponent) {}

    override fun onCreate() {
        dependencyInitializer.init()
    }
}