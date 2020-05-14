package com.itis.group11801.fedotova.smartfasting.app.di.common

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.features.news.NewsRouter
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.StatisticsRouter
import com.itis.group11801.fedotova.smartfasting.app.navigator.Navigator
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    @AppScope
    fun provideNewsRouter(navigator: Navigator): NewsRouter

    @Binds
    @AppScope
    fun provideDietRouter(navigator: Navigator): DietRouter

    @Binds
    @AppScope
    fun provideDrinkRouter(navigator: Navigator): DrinkRouter

    @Binds
    @AppScope
    fun provideStatisticsRouter(navigator: Navigator): StatisticsRouter
}
