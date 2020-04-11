package com.itis.group11801.fedotova.smartfasting.di.module

import com.itis.group11801.fedotova.smartfasting.data.remote.service.DietPlansService
import com.itis.group11801.fedotova.smartfasting.data.remote.service.DietPlansServiceImpl
import com.itis.group11801.fedotova.smartfasting.data.repository.DietPlansRepository
import com.itis.group11801.fedotova.smartfasting.data.repository.DietPlansRepositoryImpl
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DietPlansInteractor
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DietPlansInteractorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DietPlansModule {

    @Binds
    @Singleton
    abstract fun provideDietPlanService(dietPlansServiceImpl: DietPlansServiceImpl): DietPlansService

    @Binds
    @Singleton
    abstract fun provideDietPlanRepository(dietPlanRepository: DietPlansRepositoryImpl): DietPlansRepository

    @Binds
    @Singleton
    abstract fun provideDrinkInteractor(dietPlansInteractorImpl: DietPlansInteractorImpl): DietPlansInteractor
}
