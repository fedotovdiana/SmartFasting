package com.itis.group11801.fedotova.smartfasting.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.TrackerViewModel
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker.Tracker
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrackerViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var tracker: Tracker

    @MockK
    lateinit var resourceManager: ResourceManager

    @MockK
    lateinit var router: DietRouter

    @InjectMockKs
    private lateinit var viewModel: TrackerViewModel

    private val startText = "start text"
    private val stopText = "stop text"
    private val timerLength = 7200L
    private val timerText = "2 h"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(TrackerViewModel(tracker, resourceManager, router))
    }

    @Test
    fun `start timer called`() {
        every { tracker.startTimer() } just Runs

        viewModel.startTimer()

        verify { tracker.startTimer() }
    }

    @Test
    fun `stop timer called`() {
        every { tracker.stopTimer() } just Runs

        viewModel.stopTimer()

        verify { tracker.stopTimer() }
    }

    @Test
    fun `resume timer called`() {
        every { tracker.resumeTimer() } just Runs

        viewModel.resumeTimer()

        verify { tracker.resumeTimer() }
    }

    @Test
    fun `pause timer called`() {
        every { tracker.saveTimer() } just Runs

        viewModel.pauseTimer()

        verify { tracker.saveTimer() }
    }

    @Test
    fun `get timer length called`() {
        every { tracker.getTimerLength() } returns timerLength

        assertEquals(timerText, viewModel.getTimerLength())
    }

    @Test
    fun `get start text called`() {
        every { resourceManager.getString(any()) } returns startText

        assertEquals(startText, viewModel.getStartText())
    }

    @Test
    fun `get stop text called`() {
        every { resourceManager.getString(any()) } returns stopText

        assertEquals(stopText, viewModel.getStartText())
    }

    @Test
    fun `open dialog called`() {
        every { router.openConfirmStopDialogFragment() } just Runs

        viewModel.openDialog()

        verify { router.openConfirmStopDialogFragment() }
    }

    @Test
    fun `open diets called`() {
        every { router.openDietPlansFragment() } just Runs

        viewModel.openDiets()

        verify { router.openDietPlansFragment() }
    }

    @Test
    fun `check diet added called when it added`() {
        every { tracker.isDietAdded() } returns true

        assertEquals(true, viewModel.checkDietAdded())
    }

    @Test
    fun `check diet added called when it not added`() {
        every { tracker.isDietAdded() } returns false

        assertEquals(false, viewModel.checkDietAdded())
    }
}