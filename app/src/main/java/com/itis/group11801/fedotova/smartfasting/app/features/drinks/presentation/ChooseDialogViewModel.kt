package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkInteractorImpl
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ScreenScope
class ChooseDialogViewModel @Inject constructor(
    private val interactor: DrinkInteractorImpl,
    private val router: DrinkRouter
) : ViewModel() {

    private val job = Job()

    fun saveDrink(drinkSort: String, volume: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveDrinkNote(
                DrinkNote(
                    DrinkSort.valueOf(drinkSort),
                    volume.toInt(),
                    Date()
                )
            )
        }
    }

    fun closeDialog() {
        router.closeDrinkDialog()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
