package com.itis.group11801.fedotova.smartfasting.di.modules

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.data.local.AppDatabase
import com.itis.group11801.fedotova.smartfasting.data.local.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module()
class DbModule {
    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsDao(db: AppDatabase): NewsDao = db.newsDao()
}
