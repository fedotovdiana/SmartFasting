package com.itis.group11801.fedotova.smartfasting.presentation

import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.DietInfoViewModel
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DietInfoViewModelTest {

    @MockK
    lateinit var interactor: DietInteractor

    @MockK
    lateinit var router: DietRouter

    @MockK
    lateinit var resourceManager: ResourceManager

    @InjectMockKs
    private lateinit var viewModel: DietInfoViewModel

    private val color = 1

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(DietInfoViewModel(interactor, resourceManager, router))
    }

    @Test
    fun `choose diet called`() {
        coEvery { interactor.setDietID(any()) } just Runs
        every { router.openTracker() } just Runs

        viewModel.chooseDiet()

        coVerify { interactor.setDietID(any()) }
        verify { router.openTracker() }
    }

    @Test
    fun `get color called`() {
        coEvery { resourceManager.getColor(any()) } returns color

        assertEquals(color, viewModel.getDefaultColor())
    }
}