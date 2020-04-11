package com.itis.group11801.fedotova.smartfasting.data.mapper

import com.itis.group11801.fedotova.smartfasting.data.remote.model.DietPlanRemote
import com.itis.group11801.fedotova.smartfasting.domain.model.DietPlan

fun mapDietPlanRemoteToDIetPlan(dietPlanRemote: DietPlanRemote): DietPlan {
    return with(dietPlanRemote) {
        DietPlan(id, title)
    }
}
