package com.itis.group11801.fedotova.smartfasting.di.module.app

import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.domain.interactor.*
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    @AppScope
    fun provideDietPlansInteractor(dietPlansInteractorImpl: DietPlansInteractorImpl): DietPlansInteractor

    @Binds
    @AppScope
    fun provideDrinkInteractor(drinkInteractor: DrinkInteractorImpl): DrinkInteractor

    @Binds
    @AppScope
    fun provideNewsInteractor(newsInteractor: NewsInteractorImpl): NewsInteractor
}
