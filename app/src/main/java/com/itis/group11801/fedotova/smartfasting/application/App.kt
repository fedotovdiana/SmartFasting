package com.itis.group11801.fedotova.smartfasting.application

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.di.AppInjector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
