package com.itis.group11801.fedotova.smartfasting.data.mappers

import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.mapper.mapDrinkNoteLocalToDrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.mapper.mapDrinkNoteToDrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class DrinkNoteMapperTest {

    private val date = Date()

    private val drinkNoteLocal = DrinkNoteLocal(0, DrinkSort.Coffee, 1, date)

    private val drinkNote = DrinkNote(DrinkSort.Coffee, 1, date)


    @Test
    fun `map drink note local to drink note`() {
        assertEquals(drinkNote, mapDrinkNoteLocalToDrinkNote(drinkNoteLocal))
    }

    @Test
    fun `map drink note to drink note local`() {
        assertEquals(drinkNoteLocal, mapDrinkNoteToDrinkNoteLocal(drinkNote))
    }
}