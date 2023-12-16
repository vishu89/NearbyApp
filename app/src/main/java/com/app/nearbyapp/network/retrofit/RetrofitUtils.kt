package com.app.nearbyapp.network.retrofit

import com.app.nearbyapp.KeyUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {
    val client: Retrofit = Retrofit.Builder().baseUrl(KeyUtils.hostUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()
}