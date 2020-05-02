package com.itis.group11801.fedotova.smartfasting.app.features.news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.news.NewsRouter
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    private val router: NewsRouter
) : ViewModel() {

    val news: LiveData<List<News>>
        get() = interactor.getNews()

    fun updateDb() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.updateDb()
        }
    }

    fun newsClicked(url: String) {
        router.intentOpenWebsite(url)
    }
}
