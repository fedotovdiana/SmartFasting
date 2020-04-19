package com.itis.group11801.fedotova.smartfasting.feature_news.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.model.News
import javax.inject.Inject

@AppScope
class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override fun getNews(): LiveData<List<News>> {
        return newsRepository.getLocalNews()
    }

    override suspend fun updateDb() {
        newsRepository.updateDb()
    }
}
