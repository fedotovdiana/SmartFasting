package com.itis.group11801.fedotova.smartfasting.domain.interactor

import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote

interface DrinkInteractor {

    suspend fun saveDrinkNote(drinkNote: DrinkNote)
}
