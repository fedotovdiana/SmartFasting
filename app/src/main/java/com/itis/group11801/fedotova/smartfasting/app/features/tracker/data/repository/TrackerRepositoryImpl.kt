package com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.repository

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerRepository
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import javax.inject.Inject

@AppScope
class TrackerRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
) : TrackerRepository {

    override fun getTimerLength(): Long {
        return preferenceManager.getTimerLength()
    }

    override fun getRemainingSeconds(): Long {
        return preferenceManager.getRemainingSeconds()
    }

    override fun setRemainingSeconds(time: Long) {
        preferenceManager.setRemainingSeconds(time)
    }

    override fun getAlarmSetTime(): Long {
        return preferenceManager.getAlarmSetTime()
    }

    override fun setAlarmSetTime(time: Long) {
        preferenceManager.setAlarmSetTime(time)
    }
}