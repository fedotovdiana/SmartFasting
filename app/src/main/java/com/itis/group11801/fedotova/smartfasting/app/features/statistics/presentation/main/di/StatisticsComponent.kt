package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main.StatisticsFragment
import dagger.Subcomponent

@Subcomponent(modules = [StatisticsModule::class])
@ScreenScope
interface StatisticsComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): StatisticsComponent
    }

    fun inject(fragment: StatisticsFragment)
}