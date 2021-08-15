package com.werefox.catapp.app

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.werefox.core_data.mapper.CatToEntityCatMapper
import com.werefox.core_data.repositoryImpl.CatRepositoryImpl
import com.werefox.core_domain.repository.CatRepository
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_presentation.helper.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {
    private val cicerone = Cicerone.create(Router())

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context


    @Singleton
    @Provides
    fun provideRouter(): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()


    @Provides
    @Singleton
    fun provideCatsRepository(catToEntityCatMapper: CatToEntityCatMapper): CatRepository =
        CatRepositoryImpl(catToEntityCatMapper)

    @Provides
    @Singleton
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }
}