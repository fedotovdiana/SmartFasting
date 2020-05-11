package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState
import javax.inject.Inject

@AppScope
class TrackerInteractor @Inject constructor(
    private val tracker: Tracker
) {
    fun startTimer() {
        tracker.startTimer()
    }

    fun stopTimer() {
        tracker.stopTimer()
    }

    fun resumeTimer() {
        tracker.resumeTimer()
    }

    fun pauseTimer() {
        tracker.cancelTimer()
    }

    fun getProgress(): LiveData<Long> {
        return tracker.progress
    }

    fun getProgressMax(): LiveData<Long> {
        return tracker.progressMax
    }

    fun getState(): LiveData<TimerState> {
        return tracker.state
    }

    fun getProgressTime(): LiveData<Long> {
        return tracker.progressTime
    }

    fun getTimerLength(): Long {
        return tracker.getTimerLength()
    }

    fun getStartTime(): LiveData<Long> {
        return tracker.startTime
    }
}