package com.itis.group11801.fedotova.smartfasting.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.data.local.service.NewsDao
import com.itis.group11801.fedotova.smartfasting.data.mapper.mapNewsLocalToNews
import com.itis.group11801.fedotova.smartfasting.data.mapper.mapNewsToNewsLocal
import com.itis.group11801.fedotova.smartfasting.data.mapper.mapResponseResultToNews
import com.itis.group11801.fedotova.smartfasting.data.remote.service.NewsApiService
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import javax.inject.Inject

@AppScope
class NewsRepositoryImpl @Inject constructor(
    private val service: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getLocalNews(): LiveData<List<News>> {
        return newsDao.getAll().map { list ->
            list.map {
                mapNewsLocalToNews(
                    it
                )
            }
        }
    }

    override suspend fun updateDb() {
        val news = getRemoteNews()
        newsDao.insertAll(news.map {
            mapNewsToNewsLocal(
                it
            )
        })
    }

    private suspend fun getRemoteNews(): List<News> =
        service.getNews().newsResponse!!.map {
            mapResponseResultToNews(
                it
            )
        }
}
