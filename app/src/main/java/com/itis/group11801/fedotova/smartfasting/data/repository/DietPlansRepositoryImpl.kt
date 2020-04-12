package com.itis.group11801.fedotova.smartfasting.data.repository

import com.itis.group11801.fedotova.smartfasting.data.mapper.mapDietPlanRemoteToDIetPlan
import com.itis.group11801.fedotova.smartfasting.data.remote.service.DietPlansService
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.domain.model.DietPlan
import javax.inject.Inject

@AppScope
class DietPlansRepositoryImpl @Inject constructor(
    private val service: DietPlansService
) : DietPlansRepository {

    override suspend fun getDietPlans(): List<DietPlan> {
        return service.getDietPlans().map { mapDietPlanRemoteToDIetPlan(it) }
    }

    override suspend fun getDietPlan(id: Int): DietPlan {
        return mapDietPlanRemoteToDIetPlan(service.getDietPlan(id))
    }
}
