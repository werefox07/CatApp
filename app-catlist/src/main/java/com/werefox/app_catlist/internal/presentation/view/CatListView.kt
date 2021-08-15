package com.werefox.app_catlist.internal.presentation.view

import com.werefox.core_domain.entity.CatEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatListView : MvpView {

    fun showImages(catEntities: List<CatEntity>)

    fun hideLoader()

    fun showToast(text: String)
}