package com.itis.group11801.fedotova.smartfasting.feature_diets.domain

import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.model.Diet

interface DietInteractor {

    suspend fun getDietPlans(): List<Diet>

    suspend fun getDietPlan(id: Int): Diet
}
