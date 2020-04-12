package com.itis.group11801.fedotova.smartfasting.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.resources.ResourceManagerImpl
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
    fun providesSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(SP_PREF, Context.MODE_PRIVATE);
    }

    companion object {
        private const val SP_PREF = "pref"
    }
}
