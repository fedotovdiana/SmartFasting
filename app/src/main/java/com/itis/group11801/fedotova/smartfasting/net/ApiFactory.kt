package com.itis.group11801.fedotova.smartfasting.net

import com.itis.group11801.fedotova.smartfasting.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val client: OkHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel((HttpLoggingInterceptor.Level.BODY)))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(
            ApiService::class.java
        )
    }

}