package com.itis.group11801.fedotova.smartfasting.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation.DrinkTrackerViewModel
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DrinkTrackerViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var interactor: DrinkInteractor

    @MockK
    lateinit var router: DrinkRouter

    @InjectMockKs
    private lateinit var viewModel: DrinkTrackerViewModel

    private val volume = 1

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(DrinkTrackerViewModel(interactor, router))
    }

    @Test
    fun `liva data progress updated`() {
        every { interactor.getDayWaterVolume() } returns volume
        every { interactor.getWaterVolume() } returns volume

        viewModel.updateProgress()

        verify {
            interactor.getDayWaterVolume()
            interactor.getWaterVolume()
        }
        assertEquals(volume, viewModel.progress.value)
        assertEquals(volume, viewModel.progressMax.value)
    }

    @Test
    fun `open drink dialog called`() {
        every { router.openDrinkDialog() } just Runs

        viewModel.openDialog()

        verify { router.openDrinkDialog() }
    }
}