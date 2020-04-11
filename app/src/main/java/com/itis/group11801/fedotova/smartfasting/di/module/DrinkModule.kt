package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.data.repository.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.data.repository.DrinkRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DrinkInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DrinkModule {

    @Provides
    @Singleton
    fun provideDrinkRepository(drinkRepository: DrinkRepositoryImpl): DrinkRepository =
        drinkRepository

    @Provides
    @Singleton
    fun provideDrinkInteractor(drinkInteractor: DrinkInteractorImpl): DrinkInteractor =
        drinkInteractor
}
