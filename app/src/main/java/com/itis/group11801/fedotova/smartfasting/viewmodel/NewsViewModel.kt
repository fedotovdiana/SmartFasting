package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import kotlinx.coroutines.Job
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    val router: NewsRouter
) : ViewModel() {

    private val job = Job()

    val newsLiveData = interactor.getNews()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
