package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.di

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.app.di.common.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.DietPlansViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface DietPlansModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(DietPlansViewModel::class)
    fun bindDietPlansViewModel(viewModel: DietPlansViewModel): ViewModel
}
