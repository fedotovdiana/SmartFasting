package com.itis.group11801.fedotova.smartfasting.data.repository

import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.data.local.service.DrinkDao
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import javax.inject.Inject

@AppScope
class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {

    override suspend fun saveDrinkNote(drinkNote: DrinkNote) {
        drinkDao.insert(drinkNote)
    }
}
