package com.werefox.catapp.app.di

class MainContainerComponentProvider {

    lateinit var mainContainerComponent: MainContainerComponent

    companion object {
        var injectionFunction: (MainContainerComponentProvider.() -> Unit)? =
            null

        private val instance = MainContainerComponentProvider()

        internal fun newInstance(): MainContainerComponentProvider {
            injectionFunction?.invoke(instance)
            return instance
        }
    }
}