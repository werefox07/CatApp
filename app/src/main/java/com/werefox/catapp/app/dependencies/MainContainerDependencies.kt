package com.werefox.catapp.app.dependencies

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface MainContainerDependencies {

    fun getRouter(): Router
    fun getNavigatorHolder(): NavigatorHolder
}