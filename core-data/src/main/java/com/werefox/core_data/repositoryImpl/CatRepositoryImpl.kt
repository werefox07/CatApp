package com.werefox.core_data.repositoryImpl

import com.werefox.core_data.mapper.CatToEntityCatMapper
import com.werefox.core_data.network.CatsApi
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Single
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catToEntityCatMapper: CatToEntityCatMapper,
) : CatRepository {

    override fun getRemoteCats(): Single<List<CatEntity>> {
        return CatsApi.service.getImages().map { it.map { obj -> catToEntityCatMapper.map(obj) } }
    }
}