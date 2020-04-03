package com.itis.group11801.fedotova.smartfasting.data.remote.service

import com.itis.group11801.fedotova.smartfasting.BuildConfig
import com.itis.group11801.fedotova.smartfasting.data.remote.model.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("category") category: String = "health",
        @Query("apiKey") api: String = BuildConfig.API_KEY
    ): Response<ResultResponse>
}
// http://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=39ca608664a741e390df7aabf5b0e7c5

//@Singleton
//class NewsRepository @Inject constructor(
//    private val remoteSource: NewsRemoteSource,
//    private val dao: NewsDao
//) {
//    val news = resultLiveData(
//        databaseQuery = { dao.getNews() },
//        networkCall = { remoteSource.fetchData() },
//        saveResult = { dao.insert(convertToModel(it.newsResponse!!)) })
//
//    private fun convertToModel(resp: List<NewsResponse>): List<News> {
//        val news = mutableListOf<News>()
//        for (elem in resp) {
//            elem.apply {
//                news.add(
//                    News(
//                        0,
//                        source,
//                        author,
//                        title,
//                        description,
//                        url,
//                        urlToImage,
//                        publishedAt,
//                        content
//                    )
//                )
//            }
//        }
//        return news
//    }
//}
