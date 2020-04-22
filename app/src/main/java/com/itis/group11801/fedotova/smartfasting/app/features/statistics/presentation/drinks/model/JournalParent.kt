package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model

data class JournalParent(val date: String, val totalVolume: Int, val list: List<JournalChild>)
