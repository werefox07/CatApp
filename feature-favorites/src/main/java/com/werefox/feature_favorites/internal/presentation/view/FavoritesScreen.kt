package com.werefox.feature_favorites.internal.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FavoritesScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        FavoritesFragment.newInstance()
}