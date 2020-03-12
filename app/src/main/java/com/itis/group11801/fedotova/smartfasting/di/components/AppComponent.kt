package com.itis.group11801.fedotova.smartfasting.di.components

import com.itis.group11801.fedotova.smartfasting.di.modules.AppModule
import com.itis.group11801.fedotova.smartfasting.di.modules.DbModule
import com.itis.group11801.fedotova.smartfasting.di.modules.NetworkModule
import com.itis.group11801.fedotova.smartfasting.view.fragment.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DbModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(target: NewsFragment)
}