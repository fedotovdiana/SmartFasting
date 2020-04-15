package com.itis.group11801.fedotova.smartfasting.feature_diets.data.repository

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.data.mapper.mapDietRemoteToDiet
import com.itis.group11801.fedotova.smartfasting.feature_diets.data.network.DietService
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.DietRepository
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.model.Diet
import javax.inject.Inject

@AppScope
class DietRepositoryImpl @Inject constructor(
    private val service: DietService
) : DietRepository {

    override suspend fun getDietPlans(): List<Diet> {
        return service.getDietPlans().map {
            mapDietRemoteToDiet(
                it
            )
        }
    }

    override suspend fun getDietPlan(id: Int): Diet {
        return mapDietRemoteToDiet(
            service.getDietPlan(id)
        )
    }
}
