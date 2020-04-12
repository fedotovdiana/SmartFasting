package com.itis.group11801.fedotova.smartfasting.di

import com.itis.group11801.fedotova.smartfasting.application.App
import com.itis.group11801.fedotova.smartfasting.di.app.AppComponent
import com.itis.group11801.fedotova.smartfasting.di.app.DaggerAppComponent
import com.itis.group11801.fedotova.smartfasting.di.diets.DietInfoComponent
import com.itis.group11801.fedotova.smartfasting.di.diets.DietPlansComponent
import com.itis.group11801.fedotova.smartfasting.di.drink.DrinkComponent
import com.itis.group11801.fedotova.smartfasting.di.news.NewsComponent
import com.itis.group11801.fedotova.smartfasting.di.tracker.TrackerComponent
import com.itis.group11801.fedotova.smartfasting.view.activity.MainActivity
import com.itis.group11801.fedotova.smartfasting.view.fragment.*

object AppInjector {

    lateinit var appComponent: AppComponent
    private var dietPlansComponent: DietPlansComponent? = null
    private var dietInfoComponent: DietInfoComponent? = null
    private var drinkComponent: DrinkComponent? = null
    private var newsComponent: NewsComponent? = null
    private var trackerComponent: TrackerComponent? = null

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
}
