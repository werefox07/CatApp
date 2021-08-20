package com.werefox.feature_catlist.internal.presentation.view

import com.werefox.core_domain.entity.CatEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatListView : MvpView {

    fun showImages(catEntities: List<CatEntity>)

    fun setLoaderVisibility(visibility: Boolean)

    fun setLoadInProgress(value: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    fun addImages(catEntities: List<CatEntity>)
}