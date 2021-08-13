package com.werefox.app_catlist.di

import com.werefox.app_catlist.dependencies.CatListDependencies
import com.werefox.app_catlist.internal.presentation.view.CatListFragment
import dagger.Component


@CatListPresenterScope
@Component(modules = [CatListPresenterModule::class], dependencies = [CatListDependencies::class])
interface CatListPresenterComponent {

    fun inject(fragment: CatListFragment)
}