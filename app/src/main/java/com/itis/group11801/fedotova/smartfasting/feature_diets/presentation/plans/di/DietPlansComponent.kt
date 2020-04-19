package com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.plans.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.plans.DietPlansFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [DietPlansModule::class])
interface DietPlansComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): DietPlansComponent
    }

    fun inject(fragment: DietPlansFragment)
}
