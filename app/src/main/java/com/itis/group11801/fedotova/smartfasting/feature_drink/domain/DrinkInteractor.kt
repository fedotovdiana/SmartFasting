package com.itis.group11801.fedotova.smartfasting.feature_drink.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model.DrinkNote

interface DrinkInteractor {

    suspend fun saveDrinkNote(drinkNote: DrinkNote)

    fun getDrinkNotes(): LiveData<List<DrinkNote>>
}
