package com.itis.group11801.fedotova.smartfasting.di.modules

import com.itis.group11801.fedotova.smartfasting.data.local.NewsDao
import com.itis.group11801.fedotova.smartfasting.data.remote.NewsRemoteSource
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DbModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        remoteSource: NewsRemoteSource,
        dao: NewsDao
    ): NewsRepository =
        NewsRepository(remoteSource, dao)
}