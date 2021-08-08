package com.werefox.app_catlist.internal.presentation.view

import com.arellomobile.mvp.MvpView

interface CatListView : MvpView {

    fun addToFavorite()

    fun saveImage()
}