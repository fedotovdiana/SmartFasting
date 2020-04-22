package com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote

interface DrinkRepository {

    suspend fun saveDrinkNote(drinkNote: DrinkNote)

    fun getDrinkNotes(): LiveData<List<DrinkNote>>
}
