package com.itis.group11801.fedotova.smartfasting.application

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.di.components.AppComponent
import com.itis.group11801.fedotova.smartfasting.di.components.DaggerAppComponent
import com.itis.group11801.fedotova.smartfasting.di.modules.AppModule
import com.itis.group11801.fedotova.smartfasting.di.modules.NetworkModule

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
    }

    private fun initDagger(app: App): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
//            .dbModule(DbModule())
            .networkModule(NetworkModule())
            .build()
}
