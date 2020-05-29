package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import javax.inject.Inject

@AppScope
class TrackerInteractor @Inject constructor(
    private val repository: TrackerRepository
) {
    fun getRemainingSeconds(): Long {
        return repository.getRemainingSeconds()
    }

    fun getTimerLength(): Long {
        return repository.getTimerLength()
    }

    fun getAlarmSetTime(): Long {
        return repository.getAlarmSetTime()
    }

    fun setRemainingSeconds(time: Long) {
        repository.setRemainingSeconds(time)
    }

    fun resetRemainingSeconds() {
        repository.resetRemainingSeconds()
    }

    fun isDietAdded(): Boolean {
        return repository.isDietAdded()
    }

    fun setAlarmSetTime(time: Long) {
        repository.setAlarmSetTime(time)
    }

    suspend fun saveTrackerNote(trackerNote: TrackerNote) {
        repository.saveTrackerNote(trackerNote)
    }
}
