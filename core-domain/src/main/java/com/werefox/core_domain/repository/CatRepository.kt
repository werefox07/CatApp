package com.werefox.core_domain.repository

import com.werefox.core_domain.entity.CatEntity
import io.reactivex.Single

interface CatRepository {

    fun getRemoteCats(): Single<List<CatEntity>>
}