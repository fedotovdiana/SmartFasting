package com.itis.group11801.fedotova.smartfasting.app.di.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManagerImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @AppScope
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    @Provides
    @AppScope
    fun providesPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }
}
