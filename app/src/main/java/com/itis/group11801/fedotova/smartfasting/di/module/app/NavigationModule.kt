package com.itis.group11801.fedotova.smartfasting.di.module.app

import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.navigation.DietPlansRouter
import com.itis.group11801.fedotova.smartfasting.navigation.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.navigation.Navigator
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    @AppScope
    fun provideNewsRouter(navigator: Navigator): NewsRouter

    @Binds
    @AppScope
    fun provideDietPlansRouter(navigator: Navigator): DietPlansRouter

    @Binds
    @AppScope
    fun provideDrinkRouter(navigator: Navigator): DrinkRouter
}
