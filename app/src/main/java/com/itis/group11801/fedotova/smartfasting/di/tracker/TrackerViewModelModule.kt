package com.itis.group11801.fedotova.smartfasting.di.tracker

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.di.app.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.viewmodel.TrackerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
interface TrackerViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrackerViewModel::class)
    fun bindTrackerViewModel(viewModel: TrackerViewModel): ViewModel
}
