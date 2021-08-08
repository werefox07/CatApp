package com.werefox.app_catlist.internal.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.werefox.app_catlist.internal.presentation.view.CatListView
import com.werefox.core_domain.usecase.GetCatsUseCase
import com.werefox.core_presentation.presenter.BaseDisposablePresenter


@InjectViewState
class CatListPresenter(
    private val getCatsUseCase: GetCatsUseCase
) : BaseDisposablePresenter<CatListView>() {
    fun addToFavorite() {}

    fun saveImage() {}
}