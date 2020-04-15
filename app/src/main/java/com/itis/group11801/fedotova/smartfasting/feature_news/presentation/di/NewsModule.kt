package com.itis.group11801.fedotova.smartfasting.feature_news.presentation.di

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.app.di.common.ViewModelModule
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_news.presentation.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface NewsModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}
