package com.itis.group11801.fedotova.smartfasting.domain

import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerRepository
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class TrackerInteractorTest {

    @MockK
    lateinit var repository: TrackerRepository

    @InjectMockKs
    private lateinit var interactor: TrackerInteractor

    private val time = 1L
    private val trackerNote = TrackerNote(1L, Date())

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = spyk(TrackerInteractor(repository))
    }

    @Test
    fun `get remaining seconds called`() {
        every { repository.getRemainingSeconds() } returns time

        assertEquals(time, interactor.getRemainingSeconds())
    }

    @Test
    fun `get timer length called`() {
        every { repository.getTimerLength() } returns time

        assertEquals(time, interactor.getTimerLength())
    }

    @Test
    fun `get alarm set time called`() {
        every { repository.getAlarmSetTime() } returns time

        assertEquals(time, interactor.getAlarmSetTime())
    }

    @Test
    fun `set remaining seconds time called`() {
        every { repository.setRemainingSeconds(time) } just Runs

        interactor.setRemainingSeconds(time)

        verify { repository.setRemainingSeconds(time) }
    }

    @Test
    fun `reset remaining seconds time called`() {
        every { repository.resetRemainingSeconds() } just Runs

        interactor.resetRemainingSeconds()

        verify { repository.resetRemainingSeconds() }
    }

    @Test
    fun `check if diet added called`() {
        every { repository.isDietAdded() } returns true

        assertEquals(true, interactor.isDietAdded())
    }

    @Test
    fun `set alarm set time called`() {
        every { repository.setAlarmSetTime(time) } just Runs

        interactor.setAlarmSetTime(time)

        verify { repository.setAlarmSetTime(time) }
    }

    @Test
    fun `save tracker note called`() {
        coEvery { repository.saveTrackerNote(any()) } just Runs

        runBlocking { (interactor.saveTrackerNote(trackerNote)) }

        coVerify { repository.saveTrackerNote(trackerNote) }
    }
}