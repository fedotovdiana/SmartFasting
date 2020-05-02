package com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import javax.inject.Inject

@AppScope
class StatisticsInteractor @Inject constructor(
    private val drinkRepository: DrinkRepository
) {
    fun getDrinkNotes(): LiveData<List<DrinkNote>> {
        return drinkRepository.getDrinkNotes()
    }
}