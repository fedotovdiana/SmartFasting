package com.itis.group11801.fedotova.smartfasting.feature_drink.data.repository

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.mapper.mapDrinkNoteUIToDrinkNote
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.model.DrinkNoteUI
import javax.inject.Inject

@AppScope
class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {

    override suspend fun saveDrinkNote(drinkNote: DrinkNoteUI) {
        drinkDao.insert(
            mapDrinkNoteUIToDrinkNote(
                drinkNote
            )
        )
    }
}
