package com.itis.group11801.fedotova.smartfasting.presentation

import com.itis.group11801.fedotova.smartfasting.app.features.news.NewsRouter
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.news.presentation.NewsViewModel
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class NewsViewModelTest {

    @MockK
    lateinit var interactor: NewsInteractor

    @MockK
    lateinit var router: NewsRouter

    @InjectMockKs
    private lateinit var viewModel: NewsViewModel

    private val url = "https://www.qwerty.com"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(NewsViewModel(interactor, router))
    }

    @Test
    fun `open news pressed`() {
        every { router.intentOpenWebsite(any()) } just Runs

        viewModel.newsClicked(url)

        verify { router.intentOpenWebsite(url) }
    }

    @Test
    fun `update called`() {
        coEvery { interactor.update() } just Runs

        viewModel.update()

        coVerify { interactor.update() }
    }
}
