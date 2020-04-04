package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.domain.model.News
import com.itis.group11801.fedotova.smartfasting.navigation.FastsRouter
import javax.inject.Inject

class FastsViewModel @Inject constructor(
    val router: FastsRouter
) : ViewModel() {
    val news = MutableLiveData<List<News>>()
}
