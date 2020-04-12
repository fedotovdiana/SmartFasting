package com.itis.group11801.fedotova.smartfasting.di.component

import com.itis.group11801.fedotova.smartfasting.di.module.tracker.TrackerViewModelModule
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.TrackerFragment
import dagger.Subcomponent

@Subcomponent(modules = [TrackerViewModelModule::class])
@ScreenScope
interface TrackerComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): TrackerComponent
    }

    fun inject(fragment: TrackerFragment)
}
