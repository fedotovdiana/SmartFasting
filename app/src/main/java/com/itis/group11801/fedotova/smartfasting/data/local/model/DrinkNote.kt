package com.itis.group11801.fedotova.smartfasting.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "drink_notes")
data class DrinkNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "drink_sort")
    val drinkSort: DrinkSort,
    val ml: Int,
    val date: Date
)
