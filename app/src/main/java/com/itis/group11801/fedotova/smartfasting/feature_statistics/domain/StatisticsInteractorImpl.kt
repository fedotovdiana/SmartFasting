package com.itis.group11801.fedotova.smartfasting.feature_statistics.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model.DrinkNote
import javax.inject.Inject

@AppScope
class StatisticsInteractorImpl @Inject constructor(
    private val drinkRepository: DrinkRepository
) {
    fun getDrinkNotes(): LiveData<List<DrinkNote>> {
        return drinkRepository.getDrinkNotes()
    }
}