package com.werefox.core_data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CatsApi {
    val API_URL = "https://api.thecatapi.com/v1/"
    const val API_KEY = "610f8c2d-84b6-4777-ad35-bea566ea4ce3"
    lateinit var service: CatsApiService

    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(OkHttpClientFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(CatsApiService::class.java)
    }
}