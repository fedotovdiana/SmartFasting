package com.itis.group11801.fedotova.smartfasting.di.module.diets

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.di.module.app.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.viewmodel.DietInfoViewModel
import com.itis.group11801.fedotova.smartfasting.viewmodel.DietPlansViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
interface DietsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DietPlansViewModel::class)
    fun bindDietPlansViewModel(viewModel: DietPlansViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DietInfoViewModel::class)
    fun bindDietInfoViewModel(viewModel: DietInfoViewModel): ViewModel
}
