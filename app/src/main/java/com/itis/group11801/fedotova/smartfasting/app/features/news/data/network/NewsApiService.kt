package com.itis.group11801.fedotova.smartfasting.app.features.news.data.network

import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("category") category: String = "health"
    ): NewsApiResponse
}
// http://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=39ca608664a741e390df7aabf5b0e7c5
