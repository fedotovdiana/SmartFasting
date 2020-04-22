package com.itis.group11801.fedotova.smartfasting.app.features.news.data.mapper

import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.model.NewsLocal
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.network.model.NewsResponse
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.model.News

fun mapNewsApiResultToNews(response: NewsResponse): News {
    return with(response) {
        News(
            source?.name ?: "",
            author ?: "",
            title ?: "",
            description ?: "",
            url ?: "",
            urlToImage ?: "",
            publishedAt ?: "",
            content ?: ""
        )
    }
}

fun mapNewsLocalToNews(newsLocal: NewsLocal): News {
    return with(newsLocal) {
        News(
            source,
            author,
            title,
            description,
            url,
            urlToImage,
            publishedAt,
            content
        )
    }
}

fun mapNewsToNewsLocal(news: News): NewsLocal {
    return with(news) {
        NewsLocal(
            source,
            author,
            title,
            description,
            url,
            urlToImage,
            publishedAt,
            content
        )
    }
}
