package com.werefox.core_data.mapper

import com.werefox.core_data.model.Cat
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.mapper.Mapper
import javax.inject.Inject

class CatToEntityCatMapper @Inject constructor() : Mapper<Cat, CatEntity> {
    override fun map(obj: Cat): CatEntity {
        return CatEntity(obj.breeds?.map { it.name }, obj.id, obj.url)
    }
}