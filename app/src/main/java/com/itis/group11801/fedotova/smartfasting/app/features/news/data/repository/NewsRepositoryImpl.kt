package com.itis.group11801.fedotova.smartfasting.app.features.news.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.NewsDao
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper.mapNewsApiResultToNews
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper.mapNewsLocalToNews
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper.mapNewsToNewsLocal
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.NewsApiService
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.NewsRepository
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.model.News
import javax.inject.Inject

@AppScope
class NewsRepositoryImpl @Inject constructor(
    private val service: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getLocalNews(): LiveData<List<News>> {
        return newsDao.getAll().map { list -> list.map { mapNewsLocalToNews(it) } }
    }

    override suspend fun update() {
        val news = getRemoteNews()
        newsDao.insertAll(news.map { mapNewsToNewsLocal(it) })
    }

    private suspend fun getRemoteNews(): List<News> {
        return try {
            service.getNews().newsResponse!!.map { mapNewsApiResultToNews(it) }
        } catch (e: Exception) {
            listOf()
        }
    }
}
