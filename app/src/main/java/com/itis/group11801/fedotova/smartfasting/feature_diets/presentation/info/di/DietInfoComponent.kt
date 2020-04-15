package com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.DietInfoFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [DietInfoModule::class])
interface DietInfoComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): DietInfoComponent
    }

    fun inject(fragment: DietInfoFragment)
}
