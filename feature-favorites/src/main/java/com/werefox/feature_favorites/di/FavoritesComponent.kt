package com.werefox.feature_favorites.di

import com.werefox.feature_favorites.internal.presentation.view.FavoritesFragment
import dagger.Subcomponent

@FavoritesScope
@Subcomponent(modules = [FavoritesModule::class])
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)
}