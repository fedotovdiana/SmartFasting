package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.navigation.DrinkRouter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ChooseDialogViewModel @Inject constructor(
    private val interactor: DrinkInteractor,
    val router: DrinkRouter
) : ViewModel() {

    private val job = Job()

    fun saveDrink(drinkSort: String, volume: String) {
        viewModelScope.launch {
            interactor.saveDrinkNote(
                DrinkNote(
                    0,
                    DrinkSort.valueOf(drinkSort),
                    volume.toInt(),
                    Date()
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
