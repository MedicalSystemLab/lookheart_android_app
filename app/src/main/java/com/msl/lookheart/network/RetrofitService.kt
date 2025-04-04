package com.msl.lookheart.network

import retrofit2.Retrofit

class RetrofitService(retrofit: Retrofit) {
    val instance: HttpService = retrofit.create(HttpService::class.java)
}