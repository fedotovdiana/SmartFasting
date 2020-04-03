package com.itis.group11801.fedotova.smartfasting.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles")
    fun getNews(): LiveData<List<NewsLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewsLocal>)
}
