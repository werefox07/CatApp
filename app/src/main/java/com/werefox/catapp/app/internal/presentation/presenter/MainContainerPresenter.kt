package com.werefox.catapp.app.internal.presentation.presenter

import com.werefox.catapp.app.internal.presentation.view.MainContainerView
import com.werefox.catapp.app.output.MainContainerOutput
import com.werefox.core_presentation.presenter.BaseDisposablePresenter
import moxy.InjectViewState

@InjectViewState
class MainContainerPresenter(
    private val mainContainerOutput: MainContainerOutput
) : BaseDisposablePresenter<MainContainerView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mainContainerOutput.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainContainerOutput.finish()
    }
}