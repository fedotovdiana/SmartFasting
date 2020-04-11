package com.itis.group11801.fedotova.smartfasting.data.remote.service

import com.itis.group11801.fedotova.smartfasting.data.remote.model.DietPlanRemote

interface DietPlansService {

    suspend fun getDietPlans(): List<DietPlanRemote>

    suspend fun getDietPlan(id: Int): DietPlanRemote
}
