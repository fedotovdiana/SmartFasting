package com.itis.group11801.fedotova.smartfasting.app.features.news.presentation.di

import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.news.presentation.NewsFragment
import dagger.Subcomponent

@Subcomponent(modules = [NewsModule::class])
@ScreenScope
interface NewsComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): NewsComponent
    }

    fun inject(fragment: NewsFragment)
}
