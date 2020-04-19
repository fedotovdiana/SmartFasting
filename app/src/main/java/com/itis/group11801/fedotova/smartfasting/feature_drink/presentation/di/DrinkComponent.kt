package com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.ChooseDialogFragment
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.DrinkTrackerFragment
import dagger.Subcomponent

@Subcomponent(modules = [DrinkModule::class])
@ScreenScope
interface DrinkComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): DrinkComponent
    }

    fun inject(fragment: DrinkTrackerFragment)

    fun inject(fragment: ChooseDialogFragment)
}