package com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.converter

import androidx.room.TypeConverter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromLongToDate(value: Long) = Date(value)

    @TypeConverter
    fun fromDateToLong(date: Date?) = date?.time

    @TypeConverter
    fun fromIntToDrinkSort(value: Int) = enumValues<DrinkSort>()[value]

    @TypeConverter
    fun fromDrinkSortToInt(value: DrinkSort) = value.ordinal
}
