package com.itis.group11801.fedotova.smartfasting.data.repositories

import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.NewsDao
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.model.NewsLocal
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.NewsApiService
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.NewsApiResponse
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.NewsResponse
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.Source
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.repository.NewsRepositoryImpl
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NewsRepositoryImplTest {

    @MockK
    private lateinit var service: NewsApiService

    @MockK
    private lateinit var dao: NewsDao

    @MockK
    private lateinit var newsRemote: NewsApiResponse

    @InjectMockKs
    private lateinit var repository: NewsRepositoryImpl

    private val newsLocal = NewsLocal(
        "source",
        "author",
        "title",
        "desc",
        "url",
        "urlImg",
        "publishedAt",
        "content"
    )
    private val newsResponse = NewsResponse(
        Source(1, "source"),
        "author",
        "title",
        "desc",
        "url",
        "urlImg",
        "publishedAt",
        "content"
    )
    private val listLocal = listOf(newsLocal)
    private val liveDataLocal = MutableLiveData(listLocal)
    private val listNewsResponse = listOf(newsResponse)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = spyk(NewsRepositoryImpl(service, dao))
    }

    @Test
    fun `get local news called`() {
        coEvery { dao.getAll() } returns liveDataLocal

        repository.getLocalNews()

        coVerify { dao.getAll() }
    }

    @Test
    fun `update called`() {
        coEvery { dao.insertAll(any()) } just Runs
        coEvery { service.getNews() } returns newsRemote
        coEvery { newsRemote.newsResponse } returns listNewsResponse

        runBlocking { repository.update() }

        coVerify { dao.insertAll(any()) }
    }
}