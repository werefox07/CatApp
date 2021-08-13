package com.werefox.core_data.repositoryImpl

import com.werefox.core_data.network.CatsApi
import com.werefox.core_domain.entity.Cat
import com.werefox.core_domain.repository.CatRepository
import io.reactivex.Observable
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
) : CatRepository {

    override fun getRemoteCats(): Observable<List<Cat>> {
        return CatsApi.service.getImages()
    }
}