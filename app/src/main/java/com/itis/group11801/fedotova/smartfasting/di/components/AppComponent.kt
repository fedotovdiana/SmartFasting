package com.itis.group11801.fedotova.smartfasting.di.components

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.application.App
import com.itis.group11801.fedotova.smartfasting.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        DbModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
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