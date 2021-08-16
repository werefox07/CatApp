package com.werefox.core_data.mapper

import com.werefox.core_data.network.response.CatResponse
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.mapper.Mapper
import javax.inject.Inject

class CatResponseToEntityCatMapper @Inject constructor() : Mapper<CatResponse, CatEntity> {
    override fun map(obj: CatResponse): CatEntity {
        return CatEntity(obj.breedResponses?.map { it.name },
            obj.categoryResponses?.map { it.name },
            obj.id,
            obj.url)
    }
}