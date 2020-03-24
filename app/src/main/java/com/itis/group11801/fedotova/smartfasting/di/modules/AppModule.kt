package com.itis.group11801.fedotova.smartfasting.di.modules

import android.app.Application
import dagger.Module


@Module(includes = [DbModule::class, NetworkModule::class, RepositoryModule::class])
class AppModule(val app: Application)
