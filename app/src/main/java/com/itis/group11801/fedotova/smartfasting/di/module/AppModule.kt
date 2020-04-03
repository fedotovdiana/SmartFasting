package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.domain.NewsInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(userRepository: NewsRepositoryImpl): NewsRepository = userRepository

    @Provides
    @Singleton
    fun provideNewsInteractor(userInteractor: NewsInteractorImpl): NewsInteractor = userInteractor
}
