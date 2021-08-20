package com.werefox.feature_catlist.internal.presentation.view

import android.graphics.drawable.Drawable
import com.werefox.core_domain.entity.CatEntity

interface CatItemActionListener {

    fun onClickDownloadImage(drawable: Drawable?, id: String)

    fun onClickAddToFavorite(cat: CatEntity, title: String)
}