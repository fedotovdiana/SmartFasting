package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.data.Result
import com.itis.group11801.fedotova.smartfasting.data.local.News
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import javax.inject.Inject

class TrackerViewModel @Inject constructor(repository: NewsRepository) : ViewModel() {
    val news: LiveData<Result<List<News>>> = repository.news
}
