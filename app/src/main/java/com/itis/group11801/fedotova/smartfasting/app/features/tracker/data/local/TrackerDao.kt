package com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itis.group11801.fedotova.smartfasting.app.db.converter.DateConverter
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.model.TrackerNoteLocal

@TypeConverters(DateConverter::class)
@Dao
interface TrackerDao {

    @Query("SELECT * FROM tracker_notes ORDER BY date DESC")
    fun getAll(): LiveData<List<TrackerNoteLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trackerNoteLocal: TrackerNoteLocal)

    @Query("SELECT COUNT(*) FROM tracker_notes")
    fun getCount(): LiveData<Int>

    @Query("SELECT MIN(time) FROM tracker_notes")
    fun getMin(): LiveData<Long>

    @Query("SELECT MAX(time) FROM tracker_notes")
    fun getMax(): LiveData<Long>

    @Query("SELECT AVG(time) FROM tracker_notes")
    fun getAverage(): LiveData<Long>
}
