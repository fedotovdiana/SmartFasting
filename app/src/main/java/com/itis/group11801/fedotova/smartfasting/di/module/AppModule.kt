package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.data.repository.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.data.repository.DrinkRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepository
import com.itis.group11801.fedotova.smartfasting.data.repository.NewsRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DrinkInteractorImpl
import com.itis.group11801.fedotova.smartfasting.domain.interactor.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.domain.interactor.NewsInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository = newsRepository

    @Provides
    @Singleton
    fun provideDrinkRepository(drinkRepository: DrinkRepositoryImpl): DrinkRepository =
        drinkRepository

    @Provides
    @Singleton
    fun provideNewsInteractor(newsInteractor: NewsInteractorImpl): NewsInteractor = newsInteractor

    @Provides
    @Singleton
    fun provideDrinkInteractor(drinkInteractor: DrinkInteractorImpl): DrinkInteractor =
        drinkInteractor
}
