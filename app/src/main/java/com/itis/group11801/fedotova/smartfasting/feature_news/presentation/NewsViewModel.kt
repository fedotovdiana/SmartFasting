package com.itis.group11801.fedotova.smartfasting.feature_news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_news.NewsRouter
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    private val router: NewsRouter
) : ViewModel() {

    private lateinit var job: Job

    private var _news = interactor.getNews()

    val news: LiveData<List<News>>
        get() = _news

    fun updateDb() {
        job = viewModelScope.launch(Dispatchers.IO) {
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
