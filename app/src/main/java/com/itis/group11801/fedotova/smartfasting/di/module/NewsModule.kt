package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.domain.interactor.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.domain.interactor.NewsInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository = newsRepository

    @Provides
    @Singleton
    fun provideNewsInteractor(newsInteractor: NewsInteractorImpl): NewsInteractor = newsInteractor
}
