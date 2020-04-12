package com.itis.group11801.fedotova.smartfasting.di.diets

import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.DietInfoFragment
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
