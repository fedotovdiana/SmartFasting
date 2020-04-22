package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.TrackerFragment
import dagger.Subcomponent

@Subcomponent(modules = [TrackerModule::class])
@ScreenScope
interface TrackerComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): TrackerComponent
    }

    fun inject(fragment: TrackerFragment)
}
