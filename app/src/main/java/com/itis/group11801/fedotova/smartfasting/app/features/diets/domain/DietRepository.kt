package com.itis.group11801.fedotova.smartfasting.app.features.diets.domain

import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet

interface DietRepository {

    suspend fun getDietPlans(): List<Diet>

    suspend fun getDietPlan(id: Int): Diet
}
