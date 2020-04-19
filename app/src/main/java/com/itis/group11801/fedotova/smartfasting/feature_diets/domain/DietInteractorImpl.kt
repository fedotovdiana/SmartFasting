package com.itis.group11801.fedotova.smartfasting.feature_diets.domain

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.model.Diet
import javax.inject.Inject

@AppScope
class DietInteractorImpl @Inject constructor(
    private val dietRepository: DietRepository
) : DietInteractor {

    override suspend fun getDietPlans(): List<Diet> {
        return dietRepository.getDietPlans()
    }

    override suspend fun getDietPlan(id: Int): Diet {
        return dietRepository.getDietPlan(id)
    }
}
