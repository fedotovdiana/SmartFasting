package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ScreenScope
class DrinkTrackerViewModel @Inject constructor(
    private val interactor: DrinkInteractor,
    private val router: DrinkRouter
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

    fun saveDrink(sort: String, volume: String) {
        _progress.value = _progress.value?.plus(volume.toInt())
        _progressText.value = "${_progress.value.toString()}/${_progressMax.value.toString()}"

        viewModelScope.launch {
            interactor.saveDrinkNote(DrinkNote(DrinkSort.valueOf(sort), volume.toInt(), Date()))
        }
    }

    fun updateProgress() {
        _progressMax.value = interactor.getDayWaterVolume()
        _progress.value = interactor.getWaterVolume()
        _progressText.value = "${_progress.value.toString()}/${_progressMax.value.toString()}"
    }

    fun openDialog() {
        router.openDrinkDialog()
    }
}
