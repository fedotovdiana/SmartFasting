package com.itis.group11801.fedotova.smartfasting.feature_diets.data.network

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.data.network.model.DietRemote
import javax.inject.Inject

@AppScope
class DietServiceImpl @Inject constructor() :
    DietService {

    override suspend fun getDietPlans() = mockDietPlans()

    private fun mockDietPlans(): List<DietRemote> {
        return DietObject.list
    }

    override suspend fun getDietPlan(id: Int): DietRemote {
        return DietObject.list[id]
    }
}
