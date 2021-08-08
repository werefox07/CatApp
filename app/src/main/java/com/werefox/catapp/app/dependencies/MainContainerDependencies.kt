package com.werefox.catapp.app.dependencies

import com.github.terrakok.cicerone.Router

interface MainContainerDependencies {

    fun getRouter(): Router
}