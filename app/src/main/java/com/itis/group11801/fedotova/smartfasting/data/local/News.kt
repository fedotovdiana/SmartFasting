package com.itis.group11801.fedotova.smartfasting.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.itis.group11801.fedotova.smartfasting.data.remote.model.Source

@TypeConverters(SourceConverter::class)
@Entity(tableName = "articles")
data class News(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("source")
    val source: Source?,
    @field:SerializedName("author")
    val author: String?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("description")
    val description: String?,
    @field:SerializedName("url")
    val url: String?,
    @field:SerializedName("url_to_image")
    val urlToImage: String?,
    @field:SerializedName("published_at")
    val publishedAt: String?,
    @field:SerializedName("content")
    val content: String?
)