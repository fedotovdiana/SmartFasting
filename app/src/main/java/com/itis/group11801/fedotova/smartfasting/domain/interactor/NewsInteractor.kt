package com.itis.group11801.fedotova.smartfasting.domain.interactor

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.domain.model.News

interface NewsInteractor {

    fun getNews(): LiveData<List<News>>

    suspend fun updateDb()
}
