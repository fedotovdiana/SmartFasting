package com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.di

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.app.di.common.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.ChooseDialogViewModel
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.DrinkTrackerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
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
