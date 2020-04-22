package com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.mapper

import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote

fun mapDrinkNoteLocalToDrinkNote(drinkNoteLocal: DrinkNoteLocal): DrinkNote {
    return with(drinkNoteLocal) {
        DrinkNote(drinkSort, volume, date)
    }
}

fun mapDrinkNoteToDrinkNoteLocal(drinkNote: DrinkNote): DrinkNoteLocal {
    return with(drinkNote) {
        DrinkNoteLocal(0, drinkSort, volume, date)
    }
}