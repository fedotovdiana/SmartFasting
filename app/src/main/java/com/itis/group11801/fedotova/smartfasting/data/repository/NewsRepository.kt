package com.itis.group11801.fedotova.smartfasting.data.repository

import com.itis.group11801.fedotova.smartfasting.data.local.News
import com.itis.group11801.fedotova.smartfasting.data.local.NewsDao
import com.itis.group11801.fedotova.smartfasting.data.remote.NewsRemoteSource
import com.itis.group11801.fedotova.smartfasting.data.remote.model.NewsResponse
import com.itis.group11801.fedotova.smartfasting.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val remoteSource: NewsRemoteSource,
    private val dao: NewsDao
) {
    val news = resultLiveData(
        databaseQuery = { dao.getNews() },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = { dao.insertAll(convertToModel(it.newsResponse!!)) })

    private fun convertToModel(resp: List<NewsResponse>): List<News> {
        val news = mutableListOf<News>()
        for (elem in resp) {
            news.add(
                News(
                    0, elem.source,
                    elem.author, elem.title,
                    elem.description, elem.url,
                    elem.urlToImage, elem.publishedAt, elem.content
                )
            )
        }
        return news
    }
}
