package com.itis.group11801.fedotova.smartfasting.feature_drink.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.model.DrinkNoteUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ScreenScope
class ChooseDialogViewModel @Inject constructor(
    private val interactor: DrinkInteractor,
    val router: DrinkRouter
) : ViewModel() {

    private val job = Job()

    fun saveDrink(drinkSort: String, volume: String) {
        viewModelScope.launch {
            interactor.saveDrinkNote(
                DrinkNoteUI(
                    DrinkSort.valueOf(
                        drinkSort
                    ),
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
