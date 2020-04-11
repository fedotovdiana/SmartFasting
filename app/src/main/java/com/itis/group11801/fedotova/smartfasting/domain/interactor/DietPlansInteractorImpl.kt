package com.itis.group11801.fedotova.smartfasting.domain.interactor

import com.itis.group11801.fedotova.smartfasting.data.repository.DietPlansRepository
import com.itis.group11801.fedotova.smartfasting.domain.model.DietPlan
import javax.inject.Inject

class DietPlansInteractorImpl @Inject constructor(
    private val dietPlansRepository: DietPlansRepository
) : DietPlansInteractor {

    override suspend fun getDietPlans(): List<DietPlan> {
        return dietPlansRepository.getDietPlans()
    }

    override suspend fun getDietPlan(id: Int): DietPlan {
        return dietPlansRepository.getDietPlan(id)
    }
}
