package com.itis.group11801.fedotova.smartfasting.app.di

import com.itis.group11801.fedotova.smartfasting.app.MainActivity
import com.itis.group11801.fedotova.smartfasting.app.application.App
import com.itis.group11801.fedotova.smartfasting.app.di.common.AppComponent
import com.itis.group11801.fedotova.smartfasting.app.di.common.DaggerAppComponent
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.DietInfoFragment
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.di.DietInfoComponent
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.plans.DietPlansFragment
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.plans.di.DietPlansComponent
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.ChooseDialogFragment
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.DrinkTrackerFragment
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.di.DrinkComponent
import com.itis.group11801.fedotova.smartfasting.feature_news.presentation.NewsFragment
import com.itis.group11801.fedotova.smartfasting.feature_news.presentation.di.NewsComponent
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.DrinkJournalFragment
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.di.DrinkJournalComponent
import com.itis.group11801.fedotova.smartfasting.feature_tracker.presentation.TrackerFragment
import com.itis.group11801.fedotova.smartfasting.feature_tracker.presentation.di.TrackerComponent

object AppInjector {

    lateinit var appComponent: AppComponent
    private var dietPlansComponent: DietPlansComponent? = null
    private var dietInfoComponent: DietInfoComponent? = null
    private var drinkComponent: DrinkComponent? = null
    private var newsComponent: NewsComponent? = null
    private var trackerComponent: TrackerComponent? = null
    private var drinkJournalComponent: DrinkJournalComponent? = null

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

    fun injectDrinkJournalFragment(fragment: DrinkJournalFragment) {
        drinkJournalComponent?.inject(fragment)
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
}
