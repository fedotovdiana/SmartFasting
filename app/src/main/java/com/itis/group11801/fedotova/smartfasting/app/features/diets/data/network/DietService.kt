package com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote

interface DietService {

    suspend fun getDietPlans(): List<DietRemote>

    suspend fun getDietPlan(id: Int): DietRemote
}
