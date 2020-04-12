package com.itis.group11801.fedotova.smartfasting.di.tracker

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.viewmodel.TrackerViewModel
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
