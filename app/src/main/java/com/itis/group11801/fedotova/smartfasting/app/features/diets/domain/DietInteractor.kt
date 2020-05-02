package com.itis.group11801.fedotova.smartfasting.app.features.diets.domain

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import javax.inject.Inject

@AppScope
class DietInteractor @Inject constructor(
    private val dietRepository: DietRepository
) {

    suspend fun getDiets(): List<Diet> {
        return dietRepository.getDiets()
    }

    suspend fun getDiet(id: Int): Diet {
        return dietRepository.getDiet(id)
    }

    suspend fun setDietID(id: Int) {
        dietRepository.setDietID(id)
    }
}
