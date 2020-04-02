package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.data.ResultWrapper
import com.itis.group11801.fedotova.smartfasting.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    val router: NewsRouter
) : ViewModel() {
    private val job = Job()

    private val _newsLiveData = MutableLiveData<ResultWrapper<List<News>>>()
    val newsLiveData: LiveData<ResultWrapper<List<News>>> get() = _newsLiveData

    fun getNews() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { interactor.getNews() }
            _newsLiveData.value = result.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
