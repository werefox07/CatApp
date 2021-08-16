package com.werefox.core_domain.repository

import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.entity.CatFavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

interface CatRepository {

    fun getRemoteCats(): Single<List<CatEntity>>

    fun addCatToFavourite(cat: CatEntity, title: String): Completable

    fun getCatsFromFavourite(): Single<List<CatFavoriteEntity>>
}