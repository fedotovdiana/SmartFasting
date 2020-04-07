package com.itis.group11801.fedotova.smartfasting.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.itis.group11801.fedotova.smartfasting.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.resources.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(SP_PREF, Context.MODE_PRIVATE);
    }

    companion object {
        private const val SP_PREF = "pref"
    }
}
