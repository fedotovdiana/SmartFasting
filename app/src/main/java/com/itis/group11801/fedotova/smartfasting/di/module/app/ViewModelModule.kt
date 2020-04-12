package com.itis.group11801.fedotova.smartfasting.di.module.app

import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
