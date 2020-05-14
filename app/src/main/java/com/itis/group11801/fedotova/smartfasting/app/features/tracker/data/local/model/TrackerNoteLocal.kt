package com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.itis.group11801.fedotova.smartfasting.app.db.converter.DateConverter
import java.util.*

@TypeConverters(DateConverter::class)
@Entity(tableName = "tracker_notes")
data class TrackerNoteLocal(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val time: Long,
    val date: Date
)
