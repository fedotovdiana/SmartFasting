package com.itis.group11801.fedotova.smartfasting.app.di

import com.itis.group11801.fedotova.smartfasting.app.App
import com.itis.group11801.fedotova.smartfasting.app.MainActivity
import com.itis.group11801.fedotova.smartfasting.app.di.common.AppComponent
import com.itis.group11801.fedotova.smartfasting.app.di.common.DaggerAppComponent
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.DietInfoFragment
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.di.DietInfoComponent
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.DietPlansFragment
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.di.DietPlansComponent
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation.ChooseDialogFragment
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation.DrinkTrackerFragment
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation.di.DrinkComponent
import com.itis.group11801.fedotova.smartfasting.app.features.news.presentation.NewsFragment
import com.itis.group11801.fedotova.smartfasting.app.features.news.presentation.di.NewsComponent
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.DrinkJournalFragment
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.di.DrinkJournalComponent
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main.StatisticsFragment
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main.di.StatisticsComponent
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.ConfirmStopDialogFragment
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.TrackerFragment
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.di.TrackerComponent
import com.itis.group11801.fedotova.smartfasting.app.receivers.TimerExpiredReceiver
import com.itis.group11801.fedotova.smartfasting.app.receivers.TimerNotificationReceiver

object AppInjector {

    lateinit var appComponent: AppComponent
    private var dietPlansComponent: DietPlansComponent? = null
    private var dietInfoComponent: DietInfoComponent? = null
    private var drinkComponent: DrinkComponent? = null
    private var newsComponent: NewsComponent? = null
    private var trackerComponent: TrackerComponent? = null
    private var drinkJournalComponent: DrinkJournalComponent? = null
    private var statisticsComponent: StatisticsComponent? = null

    fun init(application: App) {
        DaggerAppComponent
            .builder()
            .application(application)
            .build().also { appComponent = it }
            .inject(application)
    }

    fun injectMainActivity(activity: MainActivity) {
        appComponent.inject(activity)
    }

    fun injectTimerExpiredReceiver(receiver: TimerExpiredReceiver) {
        appComponent.inject(receiver)
    }

    fun injectTimerNotificationReceiver(receiver: TimerNotificationReceiver) {
        appComponent.inject(receiver)
    }

    fun initDietPlansComponent() {
        dietPlansComponent ?: appComponent
            .plusDietPlansComponentBuilder()
            .build()
            .also {
                dietPlansComponent = it
            }
    }

    fun initDietInfoComponent() {
        dietInfoComponent ?: appComponent
            .plusDietInfoComponentBuilder()
            .build()
            .also {
                dietInfoComponent = it
            }
    }

    fun initDrinkComponent() {
        drinkComponent ?: appComponent
            .plusDrinkComponentBuilder()
            .build()
            .also {
                drinkComponent = it
            }
    }

    fun initNewsComponent() {
        newsComponent ?: appComponent
            .plusNewsComponentBuilder()
            .build()
            .also {
                newsComponent = it
            }
    }

    fun initTrackerComponent() {
        trackerComponent ?: appComponent
            .plusTrackerComponentBuilder()
            .build()
            .also {
                trackerComponent = it
            }
    }

    fun initDrinkJournalComponent() {
        drinkJournalComponent ?: appComponent
            .plusDrinkJournalComponentBuilder()
            .build()
            .also {
                drinkJournalComponent = it
            }
    }

    fun initStatisticsComponent() {
        statisticsComponent ?: appComponent
            .plusStatisticsComponentBuilder()
            .build()
            .also {
                statisticsComponent = it
            }
    }

    fun injectDrinkTrackerFragment(fragment: DrinkTrackerFragment) {
        drinkComponent?.inject(fragment)
    }

    fun injectChooseDialogFragment(fragment: ChooseDialogFragment) {
        drinkComponent?.inject(fragment)
    }

    fun injectDietPlansFragment(fragment: DietPlansFragment) {
        dietPlansComponent?.inject(fragment)
    }

    fun injectDietInfoFragment(fragment: DietInfoFragment) {
        dietInfoComponent?.inject(fragment)
    }

    fun injectNewsFragment(fragment: NewsFragment) {
        newsComponent?.inject(fragment)
    }

    fun injectTrackerFragment(fragment: TrackerFragment) {
        trackerComponent?.inject(fragment)
    }

    fun injectConfirmStopDialogFragment(fragment: ConfirmStopDialogFragment) {
        trackerComponent?.inject(fragment)
    }

    fun injectDrinkJournalFragment(fragment: DrinkJournalFragment) {
        drinkJournalComponent?.inject(fragment)
    }

    fun injectStatisticsFragment(fragment: StatisticsFragment) {
        statisticsComponent?.inject(fragment)
    }

    fun clearDietPlansComponent() {
        dietPlansComponent = null
    }

    fun clearDietInfoComponent() {
        dietInfoComponent = null
    }

    fun clearDrinkComponent() {
        drinkComponent = null
    }

    fun clearNewsComponent() {
        newsComponent = null
    }

    fun clearTrackerComponent() {
        trackerComponent = null
    }

    fun clearDrinkJournalComponent() {
        drinkJournalComponent = null
    }

    fun clearStatisticsComponent() {
        statisticsComponent = null
    }
}
