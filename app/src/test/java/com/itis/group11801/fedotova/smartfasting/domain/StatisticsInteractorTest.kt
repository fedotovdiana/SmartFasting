package com.itis.group11801.fedotova.smartfasting.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain.StatisticsInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerRepository
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StatisticsInteractorTest {

    @MockK
    lateinit var drinkRepository: DrinkRepository

    @MockK
    lateinit var trackerRepository: TrackerRepository

    @InjectMockKs
    private lateinit var interactor: StatisticsInteractor

    private val drinkNoteList = mutableListOf<DrinkNote>()
    private val drinkNoteLiveData: LiveData<List<DrinkNote>> = MutableLiveData(drinkNoteList)

    private val countLiveData: LiveData<Int> = MutableLiveData(1)
    private val countLongLiveData: LiveData<Long> = MutableLiveData(1L)

    private val drinkMostPopularLiveData: LiveData<DrinkSort?> = MutableLiveData(DrinkSort.Coffee)

    private val trackerNoteList = mutableListOf<TrackerNote>()
    private val trackerNoteLiveData: LiveData<List<TrackerNote>> = MutableLiveData(trackerNoteList)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = spyk(StatisticsInteractor(drinkRepository, trackerRepository))
    }

    @Test
    fun `get drink notes called`() {
        every { drinkRepository.getDrinkNotes() } returns drinkNoteLiveData

        assertEquals(drinkNoteLiveData, interactor.getDrinkNotes())
    }

    @Test
    fun `get drink volume total called`() {
        every { drinkRepository.getTotalVolume() } returns countLiveData

        assertEquals(countLiveData, interactor.getDrinkVolumeTotal())
    }

    @Test
    fun `get drink most popular called`() {
        every { drinkRepository.getMostPopular() } returns drinkMostPopularLiveData

        assertEquals(drinkMostPopularLiveData, interactor.getDrinkMostPopular())
    }

    @Test
    fun `check if drink added called`() {
        every { drinkRepository.isDrinkAdded() } returns true

        assertEquals(true, interactor.isDrinkAdded())
    }

    @Test
    fun `get tracker notes called`() {
        every { trackerRepository.getTrackerNotes() } returns trackerNoteLiveData

        assertEquals(trackerNoteLiveData, interactor.getTrackerNotes())
    }

    @Test
    fun `get tracker note count called`() {
        every { trackerRepository.getTrackerNotesCount() } returns countLiveData

        assertEquals(countLiveData, interactor.getTrackerNotesCount())
    }

    @Test
    fun `get tracker note min called`() {
        every { trackerRepository.getTrackerNotesMin() } returns countLongLiveData

        assertEquals(countLongLiveData, interactor.getTrackerNotesMin())
    }

    @Test
    fun `get tracker note max called`() {
        every { trackerRepository.getTrackerNotesMax() } returns countLongLiveData

        assertEquals(countLongLiveData, interactor.getTrackerNotesMax())
    }

    @Test
    fun `get tracker note average called`() {
        every { trackerRepository.getTrackerNotesAverage() } returns countLongLiveData

        assertEquals(countLongLiveData, interactor.getTrackerNotesAverage())
    }

    @Test
    fun `check if tracker note added called`() {
        every { trackerRepository.isTrackerNoteAdded() } returns true

        assertEquals(true, interactor.isTrackerNoteAdded())
    }
}