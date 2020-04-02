package com.itis.group11801.fedotova.smartfasting.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.data.ResultWrapper
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override suspend fun getNews(): LiveData<ResultWrapper<List<News>>> {
        return newsRepository.getNews()
    }
}
