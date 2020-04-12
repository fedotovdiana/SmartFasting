package com.itis.group11801.fedotova.smartfasting.di.diets

import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.DietPlansFragment
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
