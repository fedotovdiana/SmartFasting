package com.itis.group11801.fedotova.smartfasting.di.diets

import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.DietInfoFragment
import com.itis.group11801.fedotova.smartfasting.view.fragment.DietPlansFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [DietsViewModelModule::class])
interface DietsComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): DietsComponent
    }

    fun inject(fragment: DietPlansFragment)

    fun inject(fragment: DietInfoFragment)
}
