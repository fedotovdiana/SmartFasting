package com.itis.group11801.fedotova.smartfasting.di.tracker

import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.TrackerFragment
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
