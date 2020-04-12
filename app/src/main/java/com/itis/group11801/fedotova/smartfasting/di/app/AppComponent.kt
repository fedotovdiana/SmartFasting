package com.itis.group11801.fedotova.smartfasting.di.app

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.application.App
import com.itis.group11801.fedotova.smartfasting.di.diets.DietsComponent
import com.itis.group11801.fedotova.smartfasting.di.drink.DrinkComponent
import com.itis.group11801.fedotova.smartfasting.di.news.NewsComponent
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.di.tracker.TrackerComponent
import com.itis.group11801.fedotova.smartfasting.view.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DbModule::class,
        NetworkModule::class,
        AppModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    fun plusDietsComponentBuilder(): DietsComponent.Builder

    fun plusDrinkComponentBuilder(): DrinkComponent.Builder

    fun plusNewsComponentBuilder(): NewsComponent.Builder

    fun plusTrackerComponentBuilder(): TrackerComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)

    fun inject(activity: MainActivity)
}
