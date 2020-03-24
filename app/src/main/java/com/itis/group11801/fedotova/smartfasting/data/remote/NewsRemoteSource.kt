package com.itis.group11801.fedotova.smartfasting.data.remote

import com.itis.group11801.fedotova.smartfasting.data.remote.service.NewsApiService
import javax.inject.Inject

class NewsRemoteSource @Inject constructor(
    private var newsApiService: NewsApiService
) : BaseDataSource() {
    suspend fun fetchData() = getResult { newsApiService.getNews() }
}
