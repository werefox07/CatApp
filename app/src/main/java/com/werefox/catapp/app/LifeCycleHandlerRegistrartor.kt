package com.werefox.catapp.app

import android.app.Application

interface LifeCycleHandlerRegistrartor {
    fun registerLifeCycleHandler(callback: Application.ActivityLifecycleCallbacks)
}