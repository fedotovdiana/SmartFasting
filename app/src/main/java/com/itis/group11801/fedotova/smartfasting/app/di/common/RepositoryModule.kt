package com.itis.group11801.fedotova.smartfasting.app.di.common

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.repository.DietRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.repository.DrinkRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.news.data.repository.NewsRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.app.features.news.domain.NewsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @AppScope
    fun provideDietPlanRepository(dietPlanRepository: DietRepositoryImpl): DietRepository

    @Binds
    @AppScope
    fun provideDrinkRepository(drinkRepository: DrinkRepositoryImpl): DrinkRepository

    @Binds
    @AppScope
    fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}
