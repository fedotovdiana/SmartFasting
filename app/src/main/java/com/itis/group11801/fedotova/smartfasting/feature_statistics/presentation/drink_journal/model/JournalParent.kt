package com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model

data class JournalParent(val date: String, val totalVolume: Int, val list: List<JournalChild>)
