package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.navigation.DrinkRouter
import javax.inject.Inject

@ScreenScope
class DrinkTrackerViewModel @Inject constructor(
    val router: DrinkRouter
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}
