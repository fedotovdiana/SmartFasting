package com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsLocal(
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
