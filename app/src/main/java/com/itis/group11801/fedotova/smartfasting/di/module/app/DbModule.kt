package com.itis.group11801.fedotova.smartfasting.di.module.app

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.data.local.AppDatabase
import com.itis.group11801.fedotova.smartfasting.data.local.service.DrinkDao
import com.itis.group11801.fedotova.smartfasting.data.local.service.NewsDao
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
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
}
