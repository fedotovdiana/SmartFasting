package com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network

import com.itis.group11801.fedotova.smartfasting.app.features.diets.data.network.model.DietRemote

object DietObject {
    val list = mutableListOf<DietRemote>().apply {
        add(
            DietRemote(
                0,
                "12:12 intermittent"
            )
        )
        add(
            DietRemote(
                1,
                "14:10 intermittent"
            )
        )
        add(
            DietRemote(
                2,
                "16:8 intermittent"
            )
        )
        add(
            DietRemote(
                3,
                "20:4 intermittent"
            )
        )
    }
}
