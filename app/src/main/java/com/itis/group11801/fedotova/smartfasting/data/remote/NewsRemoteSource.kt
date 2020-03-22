package com.itis.group11801.fedotova.smartfasting.data.remote

import com.itis.group11801.fedotova.smartfasting.data.remote.service.NewsApiService
import javax.inject.Inject

class NewsRemoteSource @Inject constructor(
    private var newsApiService: NewsApiService
) : BaseDataSource() {
    suspend fun fetchData() = getResult { newsApiService.getNews() }
}

//    suspend fun fetchData(): Result<List<NewsResponse>> {
//        try {
//            val response = newsApiService.getNews()
//            if (response.isSuccessful) {
//                val news = response.body()?.newsResponse
//                if (news != null) {
//                    return Result.success(news)
//                }
//            }
//            return error(" ${response.code()} ${response.message()}")
//        } catch (e: Exception) {
//            return error(e.message ?: e.toString())
//
//        }
//    }
//
//    private fun <T> error(message: String): Result<T> {
//        return Result.error("Network call has failed for a following reason: $message")
//    }
