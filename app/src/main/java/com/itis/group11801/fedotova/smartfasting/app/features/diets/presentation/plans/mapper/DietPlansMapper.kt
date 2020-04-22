package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.mapper

import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietPlanUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager

fun mapDietToDietPlanUI(
    resourceManager: ResourceManager,
    diet: Diet
): DietPlanUI {
    return with(resourceManager) {
        when (diet.id) {
            0 -> DietPlanUI(
                diet.id,
                diet.title,
                getString(R.string.fast_0_description),
                R.drawable.custom_btn_purple
            )
            1 -> DietPlanUI(
                diet.id,
                diet.title,
                getString(R.string.fast_1_description),
                R.drawable.custom_btn_green
            )
            2 -> DietPlanUI(
                diet.id,
                diet.title,
                getString(R.string.fast_2_description),
                R.drawable.custom_btn_yellow
            )
            else -> DietPlanUI(
                diet.id,
                diet.title,
                getString(R.string.fast_3_description),
                R.drawable.custom_btn_blue
            )
        }
    }
}
