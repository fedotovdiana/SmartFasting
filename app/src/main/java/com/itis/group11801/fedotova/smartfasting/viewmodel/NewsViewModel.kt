package com.itis.group11801.fedotova.smartfasting.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.data.Result
import com.itis.group11801.fedotova.smartfasting.data.local.News
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor(repository: NewsRepository) : ViewModel() {

    val news: LiveData<Result<List<News>>> = repository.news

    fun onNewsClick(context: Context, url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        context.startActivity(openURL)
    }

    override fun onCleared() {
        // clean up resources
    }
}
