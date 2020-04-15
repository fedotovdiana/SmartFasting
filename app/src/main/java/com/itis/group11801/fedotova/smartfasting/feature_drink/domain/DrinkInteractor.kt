package com.itis.group11801.fedotova.smartfasting.feature_drink.domain

import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.model.DrinkNoteUI

interface DrinkInteractor {

    suspend fun saveDrinkNote(drinkNote: DrinkNoteUI)
}
