package com.itis.group11801.fedotova.smartfasting.app.di.common

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.app.db.AppDatabase
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.local.NewsDao
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.TrackerDao
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @AppScope
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Provides
    @AppScope
    fun provideNewsDao(db: AppDatabase): NewsDao = db.newsDao()

    @Provides
    @AppScope
    fun provideDrinkDao(db: AppDatabase): DrinkDao = db.drinkDao()

    @Provides
    @AppScope
    fun provideTrackerDao(db: AppDatabase): TrackerDao = db.trackerDao()
}
