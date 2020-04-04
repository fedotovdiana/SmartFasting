package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import javax.inject.Inject

class TrackerViewModel @Inject constructor(
    val router: NewsRouter
) : ViewModel() {
    val news = MutableLiveData<List<News>>()
}