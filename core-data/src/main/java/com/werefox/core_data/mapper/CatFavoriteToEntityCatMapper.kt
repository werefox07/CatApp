package com.werefox.core_data.mapper

import com.werefox.core_data.database.entity.CatFavorite
import com.werefox.core_domain.entity.CatFavoriteEntity
import com.werefox.core_domain.mapper.Mapper
import javax.inject.Inject

class CatFavoriteToEntityCatMapper @Inject constructor() : Mapper<CatFavorite, CatFavoriteEntity> {
    override fun map(obj: CatFavorite): CatFavoriteEntity {
        return CatFavoriteEntity(
            obj.id,
            obj.url,
            obj.title)
    }
}