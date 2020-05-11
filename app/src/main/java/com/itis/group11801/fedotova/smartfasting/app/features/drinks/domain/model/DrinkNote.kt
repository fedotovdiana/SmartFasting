package com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model

import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import java.util.*

data class DrinkNote(val drinkSort: DrinkSort, val volume: Int, val date: Date)
