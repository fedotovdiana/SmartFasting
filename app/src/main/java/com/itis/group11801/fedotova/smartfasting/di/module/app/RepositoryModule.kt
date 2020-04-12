package com.itis.group11801.fedotova.smartfasting.di.module.app

import com.itis.group11801.fedotova.smartfasting.data.repository.*
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @AppScope
    fun provideDietPlanRepository(dietPlanRepository: DietPlansRepositoryImpl): DietPlansRepository

    @Binds
    @AppScope
    fun provideDrinkRepository(drinkRepository: DrinkRepositoryImpl): DrinkRepository

    @Binds
    @AppScope
    fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}
