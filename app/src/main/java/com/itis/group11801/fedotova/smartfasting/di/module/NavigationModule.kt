package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.navigation.FastsRouter
import com.itis.group11801.fedotova.smartfasting.navigation.Navigator
import com.itis.group11801.fedotova.smartfasting.navigation.NewsRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @Singleton
    @Provides
    fun provideNewsRouter(navigator: Navigator): NewsRouter = navigator

    @Singleton
    @Provides
    fun provideFastsRouter(navigator: Navigator): FastsRouter = navigator
}
