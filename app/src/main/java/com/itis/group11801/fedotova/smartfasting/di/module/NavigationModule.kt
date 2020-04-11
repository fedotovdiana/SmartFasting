package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.navigation.DietPlansRouter
import com.itis.group11801.fedotova.smartfasting.navigation.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.navigation.Navigator
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

//    @Singleton
//    @Provides
//    fun provideNavigator(context: Context): Navigator = Navigator(context)

    @Singleton
    @Provides
    fun provideNewsRouter(navigator: Navigator): NewsRouter = navigator

    @Singleton
    @Provides
    fun provideDietPlansRouter(navigator: Navigator): DietPlansRouter = navigator

    @Singleton
    @Provides
    fun provideDrinkRouter(navigator: Navigator): DrinkRouter = navigator
}
