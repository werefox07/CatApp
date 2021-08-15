package com.werefox.catapp.app

import com.werefox.app_catlist.di.CatListComponent
import com.werefox.catapp.app.dependencies.MainContainerDependencies
import dagger.Component
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : MainContainerDependencies {
    fun catListComponent(): CatListComponent
}