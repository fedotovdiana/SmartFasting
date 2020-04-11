package com.itis.group11801.fedotova.smartfasting.view.recycler.fasts

data class DietPlanUI(
    val id: Int,
    val title: String,
    val desc: String,
//    val gradient: Drawable?,
    val gradient: Int,
    val color_first: Int,
    val color_second: Int
)
