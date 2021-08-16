package com.werefox.catapp.app.coordinator

import com.github.terrakok.cicerone.Router
import com.werefox.feature_catlist.internal.presentation.view.CatListScreen
import com.werefox.catapp.app.output.MainContainerOutput
import io.reactivex.disposables.CompositeDisposable

class MainPartCoordinator(
    private val router: Router
) {
    private val compositeDisposable = CompositeDisposable()

    val mainContainerOutput: MainContainerOutput = object : MainContainerOutput {

        override fun start() = this@MainPartCoordinator.start()
        override fun finish() = this@MainPartCoordinator.finish()

    }

    private fun start() {
        router.navigateTo(CatListScreen())
    }

    private fun finish() {
        compositeDisposable.clear()
    }
}