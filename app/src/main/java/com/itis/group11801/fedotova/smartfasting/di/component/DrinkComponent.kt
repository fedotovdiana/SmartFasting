package com.itis.group11801.fedotova.smartfasting.di.component

import com.itis.group11801.fedotova.smartfasting.di.module.drink.DrinkViewModelModule
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.ChooseDialogFragment
import com.itis.group11801.fedotova.smartfasting.view.fragment.DrinkTrackerFragment
import dagger.Subcomponent

@Subcomponent(modules = [DrinkViewModelModule::class])
@ScreenScope
interface DrinkComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): DrinkComponent
    }

    fun inject(fragment: DrinkTrackerFragment)
    fun inject(fragment: ChooseDialogFragment)
}