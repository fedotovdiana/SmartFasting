package com.itis.group11801.fedotova.smartfasting.view.mapper

import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.domain.model.DietPlan
import com.itis.group11801.fedotova.smartfasting.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.DietPlanUI

fun mapDietPlanToDietPlanUI(
    resourceManager: ResourceManager,
    dietPlan: DietPlan
): DietPlanUI {
    return with(resourceManager) {
        when (dietPlan.id) {
            0 -> DietPlanUI(
                dietPlan.id,
                dietPlan.title,
                getString(com.itis.group11801.fedotova.smartfasting.R.string.fast_0_description),
                R.drawable.custom_btn_purple,
                getColor(R.color.colorGradientPurpleFirst),
                getColor(R.color.colorGradientPurpleSecond)
            )
            1 -> DietPlanUI(
                dietPlan.id,
                dietPlan.title,
                getString(com.itis.group11801.fedotova.smartfasting.R.string.fast_1_description),
                R.drawable.custom_btn_green,
                getColor(R.color.colorGradientGreenFirst),
                getColor(R.color.colorGradientGreenSecond)
            )
            2 -> DietPlanUI(
                dietPlan.id,
                dietPlan.title,
                getString(com.itis.group11801.fedotova.smartfasting.R.string.fast_2_description),
                R.drawable.custom_btn_yellow,
                getColor(R.color.colorGradientYellowFirst),
                getColor(R.color.colorGradientYellowSecond)
            )
            else -> DietPlanUI(
                dietPlan.id,
                dietPlan.title,
                getString(com.itis.group11801.fedotova.smartfasting.R.string.fast_3_description),
                R.drawable.custom_btn_blue,
                getColor(R.color.colorGradientBlueFirst),
                getColor(R.color.colorGradientBlueSecond)
            )
        }
    }
}