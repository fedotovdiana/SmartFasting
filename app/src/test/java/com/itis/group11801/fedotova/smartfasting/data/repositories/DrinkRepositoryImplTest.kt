package com.itis.group11801.fedotova.smartfasting.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.repository.DrinkRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.helpers.managers.PreferenceManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class DrinkRepositoryImplTest {

    @MockK
    private lateinit var dao: DrinkDao

    @MockK
    private lateinit var preferenceManager: PreferenceManager

    @InjectMockKs
    private lateinit var repository: DrinkRepositoryImpl

    private val date = Date()
    private val dateString = "24.03.2000"
    private val listLocal = listOf(DrinkNoteLocal(0, DrinkSort.Coffee, 1, date))
    private val liveDataLocal: LiveData<List<DrinkNoteLocal>> = MutableLiveData(listLocal)
    private val drinkNote = DrinkNote(DrinkSort.Coffee, 1, date)
    private val volumeLiveData: LiveData<Int> = MutableLiveData(1)
    private val drinkMostPopularLiveData: LiveData<DrinkSort?> = MutableLiveData(DrinkSort.Coffee)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = spyk(DrinkRepositoryImpl(dao, preferenceManager))
    }

    @Test
    fun `save drink note called`() {
        coEvery { preferenceManager.getDate() } returns dateString
        coEvery { preferenceManager.setDrinkVolume(1) } just Runs
        coEvery { preferenceManager.setDate(any()) } just Runs
        coEvery { preferenceManager.setIsDrinkAdded() } just Runs
        coEvery { dao.insert(any()) } just Runs

        runBlocking { (repository.saveDrinkNote(drinkNote)) }

        coVerify { dao.insert(any()) }
    }

    @Test
    fun `get drink notes called`() {
        every { dao.getAll() } returns liveDataLocal

        repository.getDrinkNotes()

        verify { dao.getAll() }
    }

    @Test
    fun `get total volume called`() {
        coEvery { dao.getTotalVolume() } returns volumeLiveData

        assertEquals(volumeLiveData.value, repository.getTotalVolume().value)
    }

    @Test
    fun `get most popular called`() {
        coEvery { dao.getMostPopular() } returns drinkMostPopularLiveData

        assertEquals(drinkMostPopularLiveData.value, repository.getMostPopular().value)
    }

    @Test
    fun `get water volume called`() {
        every { preferenceManager.getDate() } returns dateString
        every { preferenceManager.setDate(any()) } just Runs
        every { preferenceManager.setDrinkVolume(0) } just Runs

        assertEquals(0, repository.getWaterVolume())

        verify {
            preferenceManager.setDate(any())
            preferenceManager.setDrinkVolume(0)
        }
    }

    @Test
    fun `get day water volume called`() {
        coEvery { preferenceManager.getDayWaterVolume() } returns 1

        assertEquals(1, repository.getDayWaterVolume())
    }

    @Test
    fun `check if drink called`() {
        coEvery { preferenceManager.isDrinkAdded() } returns true

        assertEquals(true, repository.isDrinkAdded())
    }
}