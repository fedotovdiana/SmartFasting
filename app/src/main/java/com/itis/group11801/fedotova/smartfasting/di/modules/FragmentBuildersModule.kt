package com.itis.group11801.fedotova.smartfasting.di.modules

import com.itis.group11801.fedotova.smartfasting.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun contributeChooseDialogFragment(): ChooseDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeDrinkTrackerFragment(): DrinkTrackerFragment

    @ContributesAndroidInjector
    abstract fun contributeStatisticsFragment(): StatisticsFragment

    @ContributesAndroidInjector
    abstract fun contributeTrackerFragment(): TrackerFragment
}
