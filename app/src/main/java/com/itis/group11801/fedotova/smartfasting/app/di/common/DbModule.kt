package com.itis.group11801.fedotova.smartfasting.app.di.common

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.app.db.AppDatabase
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.data.local.DrinkDao
import com.itis.group11801.fedotova.smartfasting.feature_news.data.local.NewsDao
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
