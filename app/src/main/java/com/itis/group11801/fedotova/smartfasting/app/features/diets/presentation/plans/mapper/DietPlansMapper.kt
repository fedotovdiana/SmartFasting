package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.mapper

import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager

fun mapDietToDietUI(
    resourceManager: ResourceManager,
    diet: Diet
): DietUI {
    return with(resourceManager) {
        when (diet.id) {
            0 -> DietUI(
                diet.id,
                diet.title,
                getString(R.string.fast_0_description),
                R.drawable.custom_btn_purple
            )
            1 -> DietUI(
                diet.id,
                diet.title,
                getString(R.string.fast_1_description),
                R.drawable.custom_btn_green
            )
            2 -> DietUI(
                diet.id,
                diet.title,
                getString(R.string.fast_2_description),
                R.drawable.custom_btn_yellow
            )
            else -> DietUI(
                diet.id,
                diet.title,
                getString(R.string.fast_3_description),
                R.drawable.custom_btn_blue
            )
        }
    }
}
