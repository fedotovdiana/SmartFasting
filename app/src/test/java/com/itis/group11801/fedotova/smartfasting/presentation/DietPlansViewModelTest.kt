package com.itis.group11801.fedotova.smartfasting.presentation

import android.os.Bundle
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.DietPlansViewModel
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.mapper.mapDietToDietUI
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class DietPlansViewModelTest {

    @MockK
    lateinit var interactor: DietInteractor

    @MockK
    lateinit var router: DietRouter

    @MockK
    lateinit var resourceManager: ResourceManager

    @MockK
    lateinit var bundle: Bundle

    @InjectMockKs
    private lateinit var viewModel: DietPlansViewModel

    @MockK
    private lateinit var list: List<DietUI>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(DietPlansViewModel(router, interactor, resourceManager))
    }

    @Test
    fun `init called`() {
        coEvery {
            interactor.getDiets().map { mapDietToDietUI(resourceManager, any()) }
        } returns list

        coVerify { interactor.getDiets() }
    }

    @Test
    fun `show diet plans called`() {
        coEvery { router.openDietInfoFragment(bundle) } just Runs

        viewModel.showDietInfo(bundle)

        coVerify { router.openDietInfoFragment(bundle) }
    }
}