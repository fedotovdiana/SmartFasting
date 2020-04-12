package com.itis.group11801.fedotova.smartfasting.di.diets

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.viewmodel.DietPlansViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DietPlansModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(DietPlansViewModel::class)
    fun bindDietPlansViewModel(viewModel: DietPlansViewModel): ViewModel
}
