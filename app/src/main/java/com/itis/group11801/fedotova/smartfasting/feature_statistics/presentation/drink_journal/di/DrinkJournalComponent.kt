package com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.DrinkJournalFragment
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