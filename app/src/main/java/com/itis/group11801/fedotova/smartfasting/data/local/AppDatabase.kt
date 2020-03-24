package com.itis.group11801.fedotova.smartfasting.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app.db"
                    )
                    .build()
            }
        }
    }
}
