package com.itis.group11801.fedotova.smartfasting.app.features.diets.domain

import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet

interface DietRepository {

    suspend fun getDiets(): List<Diet>

    suspend fun getDiet(id: Int): Diet

    suspend fun setDietID(id: Int)
}
