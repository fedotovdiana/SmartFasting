package com.itis.group11801.fedotova.smartfasting.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.TrackerDao
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.model.TrackerNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.repository.TrackerRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import com.itis.group11801.fedotova.smartfasting.app.helpers.managers.PreferenceManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class TrackerRepositoryImplTest {

    @MockK
    lateinit var dao: TrackerDao

    @MockK
    private lateinit var preferenceManager: PreferenceManager

    @InjectMockKs
    private lateinit var repository: TrackerRepositoryImpl

    private val time = 1L
    private val trackerNote = TrackerNote(1L, Date())
    private val trackerNoteLocal = TrackerNoteLocal(0, 1L, Date())
    private val countLiveData: LiveData<Int> = MutableLiveData(1)
    private val countLongLiveData: LiveData<Long> = MutableLiveData(1L)
    private val trackerNoteList = mutableListOf<TrackerNoteLocal>()
    private val trackerNoteLiveData: LiveData<List<TrackerNoteLocal>> =
        MutableLiveData(trackerNoteList)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = spyk(TrackerRepositoryImpl(dao, preferenceManager))
    }

    @Test
    fun `save tracker note called`() {
        coEvery { dao.insert(any()) } just Runs
        coEvery { preferenceManager.setIsTrackerNoteAdded() } just Runs

        runBlocking { (repository.saveTrackerNote(trackerNote)) }

        coVerify {
            dao.insert(trackerNoteLocal)
            preferenceManager.setIsTrackerNoteAdded()
        }
    }

    @Test
    fun `get tracker note count called`() {
        every { dao.getCount() } returns countLiveData

        assertEquals(countLiveData, repository.getTrackerNotesCount())
    }

    @Test
    fun `get tracker note min called`() {
        every { dao.getMin() } returns countLongLiveData

        assertEquals(countLongLiveData, repository.getTrackerNotesMin())
    }

    @Test
    fun `get tracker note max called`() {
        every { dao.getMax() } returns countLongLiveData

        assertEquals(countLongLiveData, repository.getTrackerNotesMax())
    }

    @Test
    fun `get tracker note average called`() {
        every { dao.getAverage() } returns countLongLiveData

        assertEquals(countLongLiveData, repository.getTrackerNotesAverage())
    }

    @Test
    fun `get timer length called`() {
        every { preferenceManager.getTimerLength() } returns time

        assertEquals(time, repository.getTimerLength())
    }

    @Test
    fun `get remaining seconds called`() {
        every { preferenceManager.getRemainingSeconds() } returns time

        assertEquals(time, repository.getRemainingSeconds())
    }

    @Test
    fun `set remaining seconds time called`() {
        every { preferenceManager.setRemainingSeconds(time) } just Runs

        repository.setRemainingSeconds(time)

        verify { preferenceManager.setRemainingSeconds(time) }
    }

    @Test
    fun `reset remaining seconds time called`() {
        every { preferenceManager.resetRemainingSeconds() } just Runs

        repository.resetRemainingSeconds()

        verify { preferenceManager.resetRemainingSeconds() }
    }

    @Test
    fun `get alarm set time called`() {
        every { preferenceManager.getAlarmSetTime() } returns time

        assertEquals(time, repository.getAlarmSetTime())
    }

    @Test
    fun `set alarm set time called`() {
        every { preferenceManager.setAlarmSetTime(time) } just Runs

        repository.setAlarmSetTime(time)

        verify { preferenceManager.setAlarmSetTime(time) }
    }

    @Test
    fun `check if diet added called`() {
        every { preferenceManager.isDietAdded() } returns true

        assertEquals(true, repository.isDietAdded())
    }

    @Test
    fun `check if tracker note added called`() {
        every { preferenceManager.isTrackerNoteAdded() } returns true

        assertEquals(true, repository.isTrackerNoteAdded())
    }

    @Test
    fun `get tracker notes called`() {
        every { dao.getAll() } returns trackerNoteLiveData

        repository.getTrackerNotes()

        verify { dao.getAll() }
    }
}