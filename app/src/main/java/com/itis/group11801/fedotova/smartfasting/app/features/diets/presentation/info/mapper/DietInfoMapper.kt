package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.mapper

import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.model.Diet
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.model.DietInfoUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager

fun mapDietToDietInfoUI(
    resourceManager: ResourceManager,
    diet: Diet
): DietInfoUI {
    return with(resourceManager) {
        when (diet.id) {
            0 -> DietInfoUI(
                diet.id,
                diet.title,
                getString(R.string.fast_0_description),
                getDrawable(R.drawable.custom_btn_purple),
                getColor(R.color.colorGradientPurpleSecond)
            )
            1 -> DietInfoUI(
                diet.id,
                diet.title,
                getString(R.string.fast_1_description),
                getDrawable(R.drawable.custom_btn_green),
                getColor(R.color.colorGradientGreenSecond)
            )
            2 -> DietInfoUI(
                diet.id,
                diet.title,
                getString(R.string.fast_2_description),
                getDrawable(R.drawable.custom_btn_yellow),
                getColor(R.color.colorGradientYellowSecond)
            )
            else -> DietInfoUI(
                diet.id,
                diet.title,
                getString(R.string.fast_3_description),
                getDrawable(R.drawable.custom_btn_blue),
                getColor(R.color.colorGradientBlueSecond)
            )
        }
    }
}