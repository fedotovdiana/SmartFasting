package com.itis.group11801.fedotova.smartfasting.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.NewsDao
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.model.NewsLocal

@Database(entities = [NewsLocal::class, DrinkNoteLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun drinkDao(): DrinkDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE
                ?: synchronized(AppDatabase::class) {
                    INSTANCE
                        ?: Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java,
                                "sm1.db"
                            )
                            .build()
                }
        }
    }
}