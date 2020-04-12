package com.itis.group11801.fedotova.smartfasting.di.drink

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.viewmodel.ChooseDialogViewModel
import com.itis.group11801.fedotova.smartfasting.viewmodel.DrinkTrackerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DrinkModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(DrinkTrackerViewModel::class)
    fun bindDrinkTrackerViewModel(viewModel: DrinkTrackerViewModel): ViewModel

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(ChooseDialogViewModel::class)
    fun bindChooseDialogViewModel(viewModel: ChooseDialogViewModel): ViewModel
}
