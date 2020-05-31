package com.itis.group11801.fedotova.smartfasting.domain

import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class DrinkInteractorTest {

    @MockK
    lateinit var repository: DrinkRepository

    @InjectMockKs
    private lateinit var interactor: DrinkInteractor

    private val drinkNote = DrinkNote(DrinkSort.Coffee, 1, Date())

    private val volume = 1

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = spyk(DrinkInteractor(repository))
    }

    @Test
    fun `save drink note called`() {
        coEvery { repository.saveDrinkNote(any()) } just Runs

        runBlocking { (interactor.saveDrinkNote(drinkNote)) }

        coVerify { repository.saveDrinkNote(drinkNote) }
    }

    @Test
    fun `get water volume called`() {
        every { repository.getWaterVolume() } returns volume

        assertEquals(volume, interactor.getWaterVolume())
    }

    @Test
    fun `get day water volume called`() {
        every { repository.getDayWaterVolume() } returns volume

        assertEquals(volume, interactor.getDayWaterVolume())
    }
}