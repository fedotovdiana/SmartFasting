package com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.mapper

import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.model.DrinkNoteUI

fun mapDrinkNoteToDrinkNoteUI(drinkNote: DrinkNote): DrinkNoteUI {
    return with(drinkNote) {
        DrinkNoteUI(
            drinkSort,
            volume,
            date
        )
    }
}

fun mapDrinkNoteUIToDrinkNote(drinkNoteUI: DrinkNoteUI): DrinkNote {
    return with(drinkNoteUI) {
        DrinkNote(
            0,
            drinkSort,
            volume,
            date
        )
    }
}