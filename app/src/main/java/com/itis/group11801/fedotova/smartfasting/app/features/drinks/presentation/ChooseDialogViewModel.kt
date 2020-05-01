package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkInteractorImpl
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormatToDate
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ScreenScope
class ChooseDialogViewModel @Inject constructor(
    private val interactor: DrinkInteractorImpl,
    private val router: DrinkRouter,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    fun saveDrink(drinkSort: String, volume: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveDrinkNote(
                DrinkNote(
                    DrinkSort.valueOf(drinkSort), volume.toInt(), Date()
                )
            )
        }
        if (DrinkSort.valueOf(drinkSort) == DrinkSort.WATER) {
            val date = dateFormatToDate(Date())
            if (date == preferenceManager.getDate()) {
                val newVolume = volume.toInt() + preferenceManager.getWaterVolume()
                preferenceManager.setWaterWVolume(newVolume)
            } else {
                preferenceManager.setWaterWVolume(volume.toInt())
            }
            preferenceManager.setDate(date)
        }
    }

    fun closeDialog() {
        router.closeDrinkDialog()
    }
}
