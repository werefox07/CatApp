package com.werefox.core_data.network

import com.werefox.core_domain.entity.Cat
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatsApiService {

    @Headers("x-api-key:${CatsApi.API_KEY}")
    @GET("images/search")
    fun getImages(@Query("limit") limit: Int = 16): Observable<List<Cat>>
}