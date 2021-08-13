package com.werefox.app_catlist.internal.presentation.view

import com.arellomobile.mvp.MvpView
import com.werefox.core_domain.entity.Cat

interface CatListView : MvpView {

    fun showImages(cats: List<Cat>)
}