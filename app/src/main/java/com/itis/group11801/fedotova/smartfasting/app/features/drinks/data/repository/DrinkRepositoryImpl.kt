package com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.mapper.mapDrinkNoteLocalToDrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.mapper.mapDrinkNoteToDrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.managers.PreferenceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormatToDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@AppScope
class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao,
    private val preferenceManager: PreferenceManager
) : DrinkRepository {

    override suspend fun saveDrinkNote(drinkNote: DrinkNote) {
        saveToPreference(drinkNote)
        drinkDao.insert(mapDrinkNoteToDrinkNoteLocal(drinkNote))
    }

    override fun getDrinkNotes(): LiveData<List<DrinkNote>> {
        return drinkDao.getAll().map { list ->
            list.map { mapDrinkNoteLocalToDrinkNote(it) }
        }
    }

    override fun getTotalVolume(): LiveData<Int> {
        return drinkDao.getTotalVolume()
    }

    override fun getAverageVolume(): LiveData<DrinkSort> {
        return drinkDao.getAverageVolume()
    }

    override fun getWaterVolume(): Int {
        val date = dateFormatToDate(Date())
        return if (date == preferenceManager.getDate()) {
            preferenceManager.getDrinkVolume()
        } else {
            preferenceManager.setDate(date)
            preferenceManager.setDrinkVolume(0)
            0
        }
    }

    override fun getDayWaterVolume(): Int {
        return preferenceManager.getDayWaterVolume()
    }

    private suspend fun saveToPreference(drinkNote: DrinkNote) {
        withContext(Dispatchers.IO) {
            val date = dateFormatToDate(Date())
            if (date == preferenceManager.getDate()) {
                val newVolume = drinkNote.volume + preferenceManager.getDrinkVolume()
                preferenceManager.setDrinkVolume(newVolume)
            } else {
                preferenceManager.setDrinkVolume(drinkNote.volume)
                preferenceManager.setDate(date)
            }

        }
    }
}