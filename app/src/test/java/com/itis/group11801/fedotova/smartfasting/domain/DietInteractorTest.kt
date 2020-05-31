package com.itis.group11801.fedotova.smartfasting.domain

import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietRepository
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DietInteractorTest {

    @MockK
    lateinit var repository: DietRepository

    @InjectMockKs
    private lateinit var interactor: DietInteractor

    private val diet = Diet(1, "title")
    private val list = listOf(diet)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = spyk(DietInteractor(repository))
    }

    @Test
    fun `get diets called`() {
        coEvery { repository.getDiets() } returns list

        runBlocking { (interactor.getDiets()) }

        coVerify { repository.getDiets() }
    }

    @Test
    fun `get diet called`() {
        coEvery { repository.getDiet(1) } returns diet

        assertEquals(diet, runBlocking { (interactor.getDiet(1)) })
    }

    @Test
    fun `set diet called`() {
        coEvery { repository.setDietID(any()) } just Runs

        runBlocking { (interactor.setDietID(1)) }

        coVerify { repository.setDietID(1) }
    }
}