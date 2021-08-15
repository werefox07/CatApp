package com.werefox.core_presentation.helper

import android.content.Context
import com.werefox.core_domain.uihelper.ResourceManager

class ResourceManagerImpl(private val context: Context) : ResourceManager {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}