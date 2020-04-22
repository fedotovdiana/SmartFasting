package com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.converter.DateConverter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkNoteLocal

@TypeConverters(DateConverter::class)
@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_notes ORDER BY date DESC")
    fun getAll(): LiveData<List<DrinkNoteLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drinkNoteLocal: DrinkNoteLocal)
}
