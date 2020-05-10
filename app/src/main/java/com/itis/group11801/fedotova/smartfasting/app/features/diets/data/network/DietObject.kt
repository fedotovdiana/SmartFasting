package com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote

object DietObject {
    val list = mutableListOf<DietRemote>().apply {
        add(DietRemote(0, "12h:12h"))
        add(DietRemote(1, "14h:10h"))
        add(DietRemote(2, "16h:8h"))
        add(DietRemote(3, "20h:4h"))
    }
}
