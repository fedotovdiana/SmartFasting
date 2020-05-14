package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.DrinkJournalFragment
import dagger.Subcomponent

@Subcomponent(modules = [DrinkJournalModule::class])
@ScreenScope
interface DrinkJournalComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): DrinkJournalComponent
    }

    fun inject(fragment: DrinkJournalFragment)
}