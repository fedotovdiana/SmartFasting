package com.itis.group11801.fedotova.smartfasting.di.news

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.di.ViewModelKey
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NewsModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}
