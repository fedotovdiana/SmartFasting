package com.itis.group11801.fedotova.smartfasting.domain.interactor

import com.itis.group11801.fedotova.smartfasting.domain.model.DietPlan

interface DietPlansInteractor {

    suspend fun getDietPlans(): List<DietPlan>

    suspend fun getDietPlan(id: Int): DietPlan
}
