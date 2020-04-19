package com.itis.group11801.fedotova.smartfasting.feature_news.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.model.News

interface NewsRepository {

    fun getLocalNews(): LiveData<List<News>>

    suspend fun updateDb()
}
