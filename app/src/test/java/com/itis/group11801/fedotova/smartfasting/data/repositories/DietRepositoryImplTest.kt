package com.itis.group11801.fedotova.smartfasting.data.repositories

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.DietService
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.repository.DietRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import com.itis.group11801.fedotova.smartfasting.app.helpers.managers.PreferenceManager
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DietRepositoryImplTest {

    @MockK
    private lateinit var service: DietService

    @MockK
    private lateinit var preferenceManager: PreferenceManager

    @InjectMockKs
    private lateinit var repository: DietRepositoryImpl

    private val diet = Diet(1, "title")
    private val dietRemote = DietRemote(1, "title")
    private val list = listOf(diet)
    private val listRemote = listOf(dietRemote)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = spyk(DietRepositoryImpl(service, preferenceManager))
    }

    @Test
    fun `get diets called`() {
        coEvery { service.getDiets() } returns listRemote

        assertEquals(list, runBlocking { (repository.getDiets()) })
    }

    @Test
    fun `get diet called`() {
        coEvery { service.getDiet(1) } returns dietRemote

        assertEquals(diet, runBlocking { (repository.getDiet(1)) })
    }

    @Test
    fun `set diet called`() {
        coEvery { preferenceManager.setDiet(any()) } just Runs
        coEvery { preferenceManager.setIsDietAdded() } just Runs

        runBlocking { (repository.setDietID(1)) }

        coVerify {
            preferenceManager.setDiet(1)
            preferenceManager.setIsDietAdded()
        }
    }
}