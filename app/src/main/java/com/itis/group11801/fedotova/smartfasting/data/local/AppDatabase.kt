package com.itis.group11801.fedotova.smartfasting.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.data.local.model.NewsLocal
import com.itis.group11801.fedotova.smartfasting.data.local.service.DrinkDao
import com.itis.group11801.fedotova.smartfasting.data.local.service.NewsDao

@Database(entities = [NewsLocal::class, DrinkNote::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun drinkDao(): DrinkDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "sm1.db"
                    )
                    .build()
            }
        }
    }
}
