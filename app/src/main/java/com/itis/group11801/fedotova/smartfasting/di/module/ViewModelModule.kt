package com.itis.group11801.fedotova.smartfasting.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrackerViewModel::class)
    abstract fun bindTrackerViewModel(viewModel: TrackerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FastsViewModel::class)
    abstract fun bindFastsViewModel(viewModel: FastsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FastInfoViewModel::class)
    abstract fun bindFastInfoViewModel(viewModel: FastInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DrinkTrackerViewModel::class)
    abstract fun bindDrinkTrackerViewModel(viewModel: DrinkTrackerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseDialogViewModel::class)
    abstract fun bindChooseDialogViewModel(viewModel: ChooseDialogViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
