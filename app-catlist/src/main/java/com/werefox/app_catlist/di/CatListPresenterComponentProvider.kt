package com.werefox.app_catlist.di

import javax.inject.Inject

class CatListPresenterComponentProvider {
    @Inject
    lateinit var component: CatListPresenterComponent

    companion object {
        var injectionFunction: (CatListPresenterComponentProvider.() -> Unit)? = null

        private val instance = CatListPresenterComponentProvider()

        fun getInstance(): CatListPresenterComponentProvider {
            injectionFunction?.invoke(instance)
            return instance
        }
    }
}