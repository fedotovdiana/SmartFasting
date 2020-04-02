package com.itis.group11801.fedotova.smartfasting.di.component

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.application.App
import com.itis.group11801.fedotova.smartfasting.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DbModule::class,
        NetworkModule::class,
        AppModule::class,
        NavigationModule::class,
        MainActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
