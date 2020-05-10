package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

interface TrackerRepository {

    fun getTimerLength(): Long

    fun getRemainingSeconds(): Long

    fun setRemainingSeconds(time: Long)

    fun getAlarmSetTime(): Long

    fun setAlarmSetTime(time: Long)
}