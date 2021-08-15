package com.werefox.app_catlist.di

import com.werefox.app_catlist.internal.presentation.view.CatListFragment
import dagger.Subcomponent

@CatListScope
@Subcomponent(modules = [CatListModule::class])
interface CatListComponent {

    fun inject(fragment: CatListFragment)
}