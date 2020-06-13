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

    private var _progressTextRemain: MutableLiveData<String> = MutableLiveData("")
    val progressTextRemain: LiveData<String>
        get() = _progressTextRemain

    private var _isIncorrect: MutableLiveData<Boolean> = MutableLiveData(false)
    val isIncorrect: LiveData<Boolean>
        get() = _isIncorrect

    fun saveDrink(sort: String, volume: String) {
        if (volume != "" && volume.toInt() > 50 && volume.toInt() < 2000) {
            _progress.value = _progress.value?.plus(volume.toInt())
            _progressTextRemain.value = getFormattedRemainString()

            viewModelScope.launch {
                interactor.saveDrinkNote(DrinkNote(DrinkSort.valueOf(sort), volume.toInt(), Date()))
            }
        } else {
            _isIncorrect.value = true
        }
    }

    fun updateProgress() {
        _progressMax.value = interactor.getDayWaterVolume()
        _progress.value = interactor.getWaterVolume()
        _progressTextRemain.value = getFormattedRemainString()
    }

    fun openDialog() {
        router.openDrinkDialog()
    }

    fun changeState() {
        _isIncorrect.value = false
    }

    private fun getFormattedRemainString(): String {
        var remain = _progressMax.value!! - _progress.value!!
        if (remain < 0) remain = 0
        return "$remain ml"
    }
}
