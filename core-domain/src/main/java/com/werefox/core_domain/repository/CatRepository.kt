package com.werefox.core_domain.repository

import com.werefox.core_domain.entity.Cat
import io.reactivex.Observable

interface CatRepository {

    fun getRemoteCats(): Observable<List<Cat>>
}