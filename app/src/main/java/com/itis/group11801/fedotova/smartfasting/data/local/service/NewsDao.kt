package com.itis.group11801.fedotova.smartfasting.data.local.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itis.group11801.fedotova.smartfasting.data.local.model.NewsLocal

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun getAll(): LiveData<List<NewsLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewsLocal>)
}
