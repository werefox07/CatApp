package com.werefox.catapp.app

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(
    private val context: Context
) {
    private val cicerone = Cicerone.create(Router())

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }
}