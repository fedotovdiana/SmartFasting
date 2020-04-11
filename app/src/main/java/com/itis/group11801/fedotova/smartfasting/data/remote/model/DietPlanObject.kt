package com.itis.group11801.fedotova.smartfasting.data.remote.model

object DietPlanObject {
    val list = mutableListOf<DietPlanRemote>().apply {
        add(DietPlanRemote(0, "20:4 intermittent"))
        add(DietPlanRemote(1, "16:8 intermittent"))
        add(DietPlanRemote(2, "12:12 intermittent"))
        add(DietPlanRemote(3, "10:14 intermittent"))
    }
}
