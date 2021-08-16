package com.werefox.feature_favorites.internal.presentation.presenter

import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.usecase.GetFavoritesUseCase
import com.werefox.core_presentation.presenter.BaseDisposablePresenter
import com.werefox.feature_favorites.internal.presentation.view.FavoritesView
import com.werefox.feature_favorites.output.FavoritesOutput
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val favoritesOutput: FavoritesOutput
) :
    BaseDisposablePresenter<FavoritesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFavoritesUseCase.buildUseCaseSingle(EmptyParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { favorites ->
                    viewState.showFavoriteImages(favorites)
                    viewState.hideLoader()
                },
                Throwable::printStackTrace
            )
            .unsubscribeOnDestroy()

    }

    override fun attachView(view: FavoritesView?) {
        super.attachView(view)
    }

    fun onBackPressed() {
        favoritesOutput.onBackPressed()
    }
}