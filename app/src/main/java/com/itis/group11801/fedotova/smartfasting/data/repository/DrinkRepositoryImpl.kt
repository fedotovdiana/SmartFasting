package com.itis.group11801.fedotova.smartfasting.data.repository

import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.data.local.service.DrinkDao
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {

    override suspend fun saveDrinkNote(drinkNote: DrinkNote) {
        drinkDao.insert(drinkNote)
    }
}
