package com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote

interface DietService {

    suspend fun getDiets(): List<DietRemote>

    suspend fun getDiet(id: Int): DietRemote
}
