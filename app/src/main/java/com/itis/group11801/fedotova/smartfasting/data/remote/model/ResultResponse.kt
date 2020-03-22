package com.itis.group11801.fedotova.smartfasting.data.remote.model


import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val newsResponse: List<NewsResponse>?
)

data class NewsResponse(
    @SerializedName("source")
    val source: Source?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)

data class Source(
    @SerializedName("id")
    val id: Any?,
    @SerializedName("name")
    val name: String?
)
