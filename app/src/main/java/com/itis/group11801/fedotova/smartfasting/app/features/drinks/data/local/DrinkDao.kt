package com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itis.group11801.fedotova.smartfasting.app.db.converter.DateConverter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort

@TypeConverters(DateConverter::class)
@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_notes ORDER BY date DESC")
    fun getAll(): LiveData<List<DrinkNoteLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drinkNoteLocal: DrinkNoteLocal)

    @Query("SELECT IFNULL(SUM(volume), 0) FROM drink_notes")
    fun getTotalVolume(): LiveData<Int>

    @Query(" SELECT drink_sort FROM drink_notes GROUP BY drink_sort ORDER BY COUNT(*) DESC")
    fun getMostPopular(): LiveData<DrinkSort?>
}
