package com.itis.group11801.fedotova.smartfasting.data.remote.service

import com.itis.group11801.fedotova.smartfasting.data.remote.model.DietPlanObject
import com.itis.group11801.fedotova.smartfasting.data.remote.model.DietPlanRemote
import javax.inject.Inject

class DietPlansServiceImpl @Inject constructor() : DietPlansService {

    override suspend fun getDietPlans() = mockDietPlans()

    private fun mockDietPlans(): List<DietPlanRemote> {
        return DietPlanObject.list
    }

    override suspend fun getDietPlan(id: Int): DietPlanRemote {
        return DietPlanObject.list[id]
    }
}
