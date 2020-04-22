package com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.converter.DateConverter
import java.util.*

@TypeConverters(DateConverter::class)
@Entity(tableName = "drink_notes")
data class DrinkNoteLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "drink_sort")
    val drinkSort: DrinkSort,
    val volume: Int,
    val date: Date
)
