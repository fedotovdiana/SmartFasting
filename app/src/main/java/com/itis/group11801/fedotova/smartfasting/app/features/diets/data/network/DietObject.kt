package com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote

object DietObject {
    val list = mutableListOf<DietRemote>().apply {
        add(
            DietRemote(
                0,
                "20:4 intermittent"
            )
        )
        add(
            DietRemote(
                1,
                "16:8 intermittent"
            )
        )
        add(
            DietRemote(
                2,
                "12:12 intermittent"
            )
        )
        add(
            DietRemote(
                3,
                "10:14 intermittent"
            )
        )
    }
}
