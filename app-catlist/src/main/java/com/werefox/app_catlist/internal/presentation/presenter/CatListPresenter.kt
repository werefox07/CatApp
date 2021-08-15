package com.werefox.app_catlist.internal.presentation.presenter

import com.werefox.app_catlist.internal.presentation.view.CatListView
import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.usecase.GetCatsUseCase
import com.werefox.core_presentation.presenter.BaseDisposablePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class CatListPresenter @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
) : BaseDisposablePresenter<CatListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCatsUseCase.buildUseCaseSingle(EmptyParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { catsImages ->
                    viewState.showImages(catsImages)
                    viewState.hideLoader()
                },
                Throwable::printStackTrace
            )
            .unsubscribeOnDestroy()
    }

    override fun attachView(view: CatListView?) {
        super.attachView(view)
    }

    fun addToFavorite() {}

    fun saveImage() {}

}