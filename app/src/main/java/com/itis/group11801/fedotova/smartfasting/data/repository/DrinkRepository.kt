package com.itis.group11801.fedotova.smartfasting.data.repository

import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote

interface DrinkRepository {

    suspend fun saveDrinkNote(drinkNote: DrinkNote)
}
