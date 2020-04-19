package com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.di

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.app.di.common.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.DrinkJournalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface DrinkJournalModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(DrinkJournalViewModel::class)
    fun bindNewsViewModel(viewModel: DrinkJournalViewModel): ViewModel
}