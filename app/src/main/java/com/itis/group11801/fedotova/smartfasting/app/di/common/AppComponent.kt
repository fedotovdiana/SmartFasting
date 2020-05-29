package com.itis.group11801.fedotova.smartfasting.app.di.common

import android.app.Application
import com.itis.group11801.fedotova.smartfasting.app.App
import com.itis.group11801.fedotova.smartfasting.app.MainActivity
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.di.DietInfoComponent
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.di.DietPlansComponent
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation.di.DrinkComponent
import com.itis.group11801.fedotova.smartfasting.app.features.news.presentation.di.NewsComponent
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.di.DrinkJournalComponent
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main.di.StatisticsComponent
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.di.TrackerComponent
import com.itis.group11801.fedotova.smartfasting.app.helpers.receivers.TimerExpiredReceiver
import com.itis.group11801.fedotova.smartfasting.app.helpers.receivers.TimerStopReceiver
import com.itis.group11801.fedotova.smartfasting.app.helpers.services.TimerExpiredJobIntentService
import com.itis.group11801.fedotova.smartfasting.app.helpers.services.TimerStopJobIntentService
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DbModule::class,
        NetworkModule::class,
        AppModule::class,
        RepositoryModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    fun plusDietPlansComponentBuilder(): DietPlansComponent.Builder
    fun plusDietInfoComponentBuilder(): DietInfoComponent.Builder
    fun plusDrinkComponentBuilder(): DrinkComponent.Builder
    fun plusNewsComponentBuilder(): NewsComponent.Builder
    fun plusTrackerComponentBuilder(): TrackerComponent.Builder
    fun plusDrinkJournalComponentBuilder(): DrinkJournalComponent.Builder
    fun plusStatisticsComponentBuilder(): StatisticsComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
    fun inject(activity: MainActivity)
    fun inject(receiver: TimerExpiredReceiver)
    fun inject(receiver: TimerStopReceiver)
    fun inject(service: TimerStopJobIntentService)
    fun inject(service: TimerExpiredJobIntentService)
}
