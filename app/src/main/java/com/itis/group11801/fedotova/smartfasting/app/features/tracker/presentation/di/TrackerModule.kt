package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.di

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.TrackerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TrackerModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(TrackerViewModel::class)
    fun bindTrackerViewModel(viewModel: TrackerViewModel): ViewModel
}
