package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import javax.inject.Inject

class TrackerViewModel @Inject constructor(interactor: NewsInteractor) : ViewModel() {
    val news = MutableLiveData<List<News>>()
}
