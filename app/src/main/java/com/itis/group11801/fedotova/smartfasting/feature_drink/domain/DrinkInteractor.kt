package com.itis.group11801.fedotova.smartfasting.feature_drink.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model.JournalParent

interface DrinkInteractor {

    suspend fun saveDrinkNote(drinkNote: DrinkNote)

    fun getJournal(): LiveData<List<JournalParent>>
}
