package com.itis.group11801.fedotova.smartfasting.feature_drink.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.mapper.mapDrinkNoteLocalToDrinkNote
import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.mapper.mapDrinkNoteToDrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model.DrinkNote
import javax.inject.Inject

@AppScope
class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {

    override suspend fun saveDrinkNote(drinkNote: DrinkNote) {
        drinkDao.insert(mapDrinkNoteToDrinkNoteLocal(drinkNote))
    }

    override fun getDrinkNotes(): LiveData<List<DrinkNote>> {
        return drinkDao.getAll().map { list ->
            list.map { mapDrinkNoteLocalToDrinkNote(it) }
        }
    }
}