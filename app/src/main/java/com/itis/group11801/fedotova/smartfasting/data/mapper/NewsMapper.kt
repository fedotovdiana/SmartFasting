package com.itis.group11801.fedotova.smartfasting.data.mapper

import com.itis.group11801.fedotova.smartfasting.data.local.model.NewsLocal
import com.itis.group11801.fedotova.smartfasting.data.remote.model.NewsResponse
import com.itis.group11801.fedotova.smartfasting.domain.model.News

fun mapResponseResultToNews(response: NewsResponse): News {
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
