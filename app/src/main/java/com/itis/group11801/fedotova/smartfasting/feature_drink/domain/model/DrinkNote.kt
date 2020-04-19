package com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model

import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.model.DrinkSort
import java.util.*

data class DrinkNote(
    val drinkSort: DrinkSort,
    val volume: Int,
    val date: Date
)