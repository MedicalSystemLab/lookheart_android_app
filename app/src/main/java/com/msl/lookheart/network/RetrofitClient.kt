package com.msl.lookheart.network

import com.google.gson.Gson
import com.msl.lookheart.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient(
    url: String = BuildConfig.API_BASE_URL,
    gson: Gson = Gson()
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val instance: HttpService = retrofit.create(HttpService::class.java)
}