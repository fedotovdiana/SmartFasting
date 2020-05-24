package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.model

import android.graphics.drawable.Drawable

data class DietInfoUI(
    val id: Int,
    val title: String,
    val desc: String,
    val gradient: Drawable?,
    val color: Int,
    val img: Int
)