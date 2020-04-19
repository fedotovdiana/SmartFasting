package com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.di

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.app.di.common.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.DietInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface DietInfoModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(DietInfoViewModel::class)
    fun bindDietInfoViewModel(viewModel: DietInfoViewModel): ViewModel
}