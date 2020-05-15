package com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote

interface DrinkRepository {

    suspend fun saveDrinkNote(drinkNote: DrinkNote)

    fun getDrinkNotes(): LiveData<List<DrinkNote>>

    fun getTotalVolume(): LiveData<Int>

    fun getAverageVolume(): LiveData<DrinkSort?>

    fun getWaterVolume(): Int

    fun getDayWaterVolume(): Int
}
