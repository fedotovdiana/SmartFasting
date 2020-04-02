package com.itis.group11801.fedotova.smartfasting.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.data.ResultWrapper
import com.itis.group11801.fedotova.smartfasting.domain.model.News

interface NewsInteractor {

    suspend fun getNews(): LiveData<ResultWrapper<List<News>>>
}
