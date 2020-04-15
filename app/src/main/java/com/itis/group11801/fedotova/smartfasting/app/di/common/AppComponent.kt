package com.itis.group11801.fedotova.smartfasting.app.di.common

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.app.MainActivity
import com.itis.group11801.fedotova.smartfasting.app.application.App
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.di.DietInfoComponent
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.plans.di.DietPlansComponent
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.di.DrinkComponent
import com.itis.group11801.fedotova.smartfasting.feature_news.presentation.di.NewsComponent
import com.itis.group11801.fedotova.smartfasting.feature_tracker.presentation.di.TrackerComponent
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

    fun plusDietPlansComponentBuilder(): DietPlansComponent.Builder

    fun plusDietInfoComponentBuilder(): DietInfoComponent.Builder

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
