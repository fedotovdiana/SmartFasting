package com.itis.group11801.fedotova.smartfasting.app.features.diets.data.repository

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.mapper.mapDietRemoteToDiet
import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.DietService
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietRepository
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import javax.inject.Inject

@AppScope
class DietRepositoryImpl @Inject constructor(
    private val service: DietService,
    private val preferenceManager: PreferenceManager
) : DietRepository {

    override suspend fun getDiets(): List<Diet> {
        return service.getDiets().map { mapDietRemoteToDiet(it) }
    }

    override suspend fun getDiet(id: Int): Diet {
        return mapDietRemoteToDiet(service.getDiet(id))
    }

    override suspend fun setDietID(id: Int) {
        preferenceManager.setDietID(id)
    }
}
