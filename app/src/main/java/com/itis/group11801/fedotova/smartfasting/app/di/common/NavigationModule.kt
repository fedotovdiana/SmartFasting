package com.itis.group11801.fedotova.smartfasting.app.di.common

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.navigator.Navigator
import com.itis.group11801.fedotova.smartfasting.feature_diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.feature_drink.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.feature_news.NewsRouter
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
}
