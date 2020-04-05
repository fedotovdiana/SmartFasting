package com.itis.group11801.fedotova.smartfasting.data.local.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itis.group11801.fedotova.smartfasting.data.local.converter.DateConverter
import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote

@TypeConverters(DateConverter::class)
@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_notes ORDER BY date DESC")
    fun getAll(): LiveData<List<DrinkNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drinkNote: DrinkNote)
}
