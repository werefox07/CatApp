package com.werefox.feature_catlist.internal.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CatListScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        CatListFragment.newInstance()
}