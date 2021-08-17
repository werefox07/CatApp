package com.werefox.feature_favorites.internal.presentation.view

import com.werefox.core_domain.entity.CatFavoriteEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoritesView : MvpView {

    fun showFavoriteImages(favorites: List<CatFavoriteEntity>)

    fun setVisibilityLoader(visibility: Boolean)

    fun setVisibilityStub(visibility: Boolean)
}