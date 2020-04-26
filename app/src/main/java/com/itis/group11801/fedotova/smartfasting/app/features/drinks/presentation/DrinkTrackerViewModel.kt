package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import javax.inject.Inject

@ScreenScope
class DrinkTrackerViewModel @Inject constructor(
    private val router: DrinkRouter,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var _progress: MutableLiveData<Int> = MutableLiveData(0)
    val progress: LiveData<Int>
        get() = _progress

    private var _progressMax: MutableLiveData<Int> = MutableLiveData(0)
    val progressMax: LiveData<Int>
        get() = _progressMax

    private var _progressText: MutableLiveData<String> = MutableLiveData("")
    val progressText: LiveData<String>
        get() = _progressText

    fun openDrinkDialog() {
        router.openDrinkDialog()
    }

    fun updateProgress() {
        _progressMax.value = preferenceManager.getDayWaterVolume()
        _progress.value = preferenceManager.getWaterVolume()
        _progressText.value = preferenceManager.getDate()!!
//        _progressText.value = "${_progress.value.toString()}/${_progressMax.value.toString()}"
    }
}
