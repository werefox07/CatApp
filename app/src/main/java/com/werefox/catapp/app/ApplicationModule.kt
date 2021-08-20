package com.werefox.catapp.app

import android.content.Context
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.werefox.catapp.app.coordinator.CatListPartCoordinator
import com.werefox.catapp.app.coordinator.FavoritesPartCoordinator
import com.werefox.core_data.database.CatDao
import com.werefox.core_data.database.CatsDatabase
import com.werefox.core_data.mapper.CatFavoriteToEntityCatMapper
import com.werefox.core_data.mapper.CatResponseToEntityCatMapper
import com.werefox.core_data.network.CatsApi.API_URL
import com.werefox.core_data.network.CatsApiService
import com.werefox.core_data.network.OkHttpClientFactory
import com.werefox.core_data.repositoryImpl.CatRepositoryImpl
import com.werefox.core_domain.repository.CatRepository
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_presentation.helper.ResourceManagerImpl
import com.werefox.feature_catlist.output.CatListOutput
import com.werefox.feature_favorites.output.FavoritesOutput
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context


    @Singleton
    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideCatsRepository(
        catResponseToEntityCatMapper: CatResponseToEntityCatMapper,
        catFavoriteToEntityCatMapper: CatFavoriteToEntityCatMapper,
        catDao: CatDao,
        catRetService: CatsApiService
    ): CatRepository =
        CatRepositoryImpl(catResponseToEntityCatMapper, catFavoriteToEntityCatMapper, catDao, catRetService)

    @Provides
    @Singleton
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideRestaurantsDatabase(context: Context): CatsDatabase =
        Room.databaseBuilder(context, CatsDatabase::class.java, "cats_db").build()

    @Provides
    @Singleton
    fun provideRestaurantsDao(db: CatsDatabase): CatDao = db.catDao()

    @Singleton
    @Provides
    internal fun catListPartCoordinator(router: Router): CatListOutput =
        CatListPartCoordinator(router)

    @Singleton
    @Provides
    internal fun favoritesPartCoordinator(router: Router): FavoritesOutput =
        FavoritesPartCoordinator(router)

    @Singleton
    @Provides
    internal fun getApiService(retrofit: Retrofit): CatsApiService =
        retrofit.create(CatsApiService::class.java)


    @Singleton
    @Provides
    internal fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(OkHttpClientFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}