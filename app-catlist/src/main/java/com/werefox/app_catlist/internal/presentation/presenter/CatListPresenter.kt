package com.werefox.app_catlist.internal.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.werefox.app_catlist.internal.presentation.view.CatListView
import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.usecase.GetCatsUseCase
import com.werefox.core_presentation.presenter.BaseDisposablePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@InjectViewState
class CatListPresenter(
    private val getCatsUseCase: GetCatsUseCase
) : BaseDisposablePresenter<CatListView>() {

    fun addToFavorite() {}

    fun saveImage() {}

    fun loadImage() {
        getCatsUseCase.buildUseCaseObservable(EmptyParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { catsImages -> viewState.showImages(catsImages) },
                {
                    it.printStackTrace()
                }
            )
            .unsubscribeOnDestroy()
    }

}