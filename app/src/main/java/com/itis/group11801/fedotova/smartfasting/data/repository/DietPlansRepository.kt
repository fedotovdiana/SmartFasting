package com.itis.group11801.fedotova.smartfasting.data.repository

import com.itis.group11801.fedotova.smartfasting.domain.model.DietPlan

interface DietPlansRepository {

    suspend fun getDietPlans(): List<DietPlan>

    suspend fun getDietPlan(id: Int): DietPlan
}
