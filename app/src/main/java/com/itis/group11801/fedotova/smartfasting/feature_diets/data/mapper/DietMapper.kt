package com.itis.group11801.fedotova.smartfasting.feature_diets.data.mapper

import com.itis.group11801.fedotova.smartfasting.feature_diets.data.network.model.DietRemote
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.model.Diet

fun mapDietRemoteToDiet(dietRemote: DietRemote): Diet {
    return with(dietRemote) {
        Diet(id, title)
    }
}
