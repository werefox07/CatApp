package com.werefox.core_data.repositoryImpl

import com.werefox.core_data.database.CatDao
import com.werefox.core_data.database.entity.CatFavorite
import com.werefox.core_data.mapper.CatFavoriteToEntityCatMapper
import com.werefox.core_data.mapper.CatResponseToEntityCatMapper
import com.werefox.core_data.network.CatsApi
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.entity.CatFavoriteEntity
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catResponseToEntityCatMapper: CatResponseToEntityCatMapper,
    private val catFavoriteToEntityCatMapper: CatFavoriteToEntityCatMapper,
    private val catDao: CatDao,
) : CatRepository {

    override fun getRemoteCats(): Single<List<CatEntity>> {
        return CatsApi.service.getImages()
            .map { it.map { obj -> catResponseToEntityCatMapper.map(obj) } }
    }

    override fun addCatToFavourite(cat: CatEntity, title: String): Completable =
        catDao.saveCatToFavorite(CatFavorite(cat.id, cat.url, title))


    override fun getCatsFromFavourite(): Single<List<CatFavoriteEntity>> =
        catDao.getCatsFromFavourite()
            .map { list -> list.map { element -> catFavoriteToEntityCatMapper.map(element) } }
}