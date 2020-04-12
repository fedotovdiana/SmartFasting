package com.itis.group11801.fedotova.smartfasting.di.component

import com.itis.group11801.fedotova.smartfasting.di.module.news.NewsViewModelModule
import com.itis.group11801.fedotova.smartfasting.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.view.fragment.NewsFragment
import dagger.Subcomponent

@Subcomponent(modules = [NewsViewModelModule::class])
@ScreenScope
interface NewsComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): NewsComponent
    }

    fun inject(fragment: NewsFragment)
}
