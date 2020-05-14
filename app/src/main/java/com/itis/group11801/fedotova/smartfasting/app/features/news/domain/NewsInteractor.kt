package com.itis.group11801.fedotova.smartfasting.app.features.news.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.model.News
import javax.inject.Inject

@AppScope
class NewsInteractor @Inject constructor(
    private val newsRepository: NewsRepository
) {

    fun getNews(): LiveData<List<News>> {
        return newsRepository.getLocalNews()
    }

    suspend fun update() {
        newsRepository.update()
    }
}
