package com.itis.group11801.fedotova.smartfasting.data.mappers

import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.model.NewsLocal
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper.mapNewsApiResultToNews
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper.mapNewsLocalToNews
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper.mapNewsToNewsLocal
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.NewsResponse
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.Source
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.model.News
import junit.framework.Assert.assertEquals
import org.junit.Test

class NewsMapperTest {

    private val source = Source(1, "source")

    private val newsResponse = NewsResponse(
        source,
        "author",
        "title",
        "desc",
        "url",
        "urlImg",
        "publishedAt",
        "content"
    )

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

    @Test
    fun `map news local to drink news`() {
        assertEquals(news, mapNewsLocalToNews(newsLocal))
    }

    @Test
    fun `map news to drink news local`() {
        assertEquals(newsLocal, mapNewsToNewsLocal(news))
    }

    @Test
    fun `map news api result local to drink news`() {
        assertEquals(news, mapNewsApiResultToNews(newsResponse))
    }
}
