package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.domain.interactor.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    val router: NewsRouter
) : ViewModel() {

    private lateinit var job: Job

    private var _news = interactor.getNews()

    val news: LiveData<List<News>>
        get() = _news

    fun updateDb() {
        job = viewModelScope.launch {
            interactor.updateDb()
        }
    }

    fun newsClicked(url: String) {
        router.intentOpenWebsite(url)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
