package com.itis.group11801.fedotova.smartfasting.domain

import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.NewsRepository
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.model.News
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NewsInteractorTest {

    @MockK
    lateinit var repository: NewsRepository

    @InjectMockKs
    private lateinit var interactor: NewsInteractor

    private val news = News(
        "source",
        "author",
        "title",
        "desc",
        "url",
        "urlImg",
        "publishedAt",
        "content"
    )

    private val list = listOf(news)
    private val liveData = MutableLiveData(list)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        interactor = spyk(NewsInteractor(repository))
    }

    @Test
    fun `get news called`() {
        coEvery { repository.getLocalNews() } returns liveData

        runBlocking { (interactor.getNews()) }

        coVerify { repository.getLocalNews() }
    }

    @Test
    fun `set diet called`() {
        coEvery { repository.update() } just Runs

        runBlocking { (interactor.update()) }

        coVerify { repository.update() }
    }
}