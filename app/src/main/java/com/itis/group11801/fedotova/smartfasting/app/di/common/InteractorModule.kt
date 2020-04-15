package com.itis.group11801.fedotova.smartfasting.app.di.common

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.DietInteractorImpl
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkInteractorImpl
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.NewsInteractor
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.NewsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    @AppScope
    fun provideDietInteractor(dietInteractorImpl: DietInteractorImpl): DietInteractor

    @Binds
    @AppScope
    fun provideDrinkInteractor(drinkInteractor: DrinkInteractorImpl): DrinkInteractor

    @Binds
    @AppScope
    fun provideNewsInteractor(newsInteractor: NewsInteractorImpl): NewsInteractor
}
