package com.werefox.app_catlist.internal.presentation.view

import android.graphics.drawable.Drawable

interface CatItemActionListener {

    fun onClickSave(drawable: Drawable, id: String)

    fun onClickAddToFavorite(imageUrl: String)
}