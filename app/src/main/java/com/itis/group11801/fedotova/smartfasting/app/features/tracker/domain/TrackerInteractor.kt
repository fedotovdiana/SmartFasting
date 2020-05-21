package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import javax.inject.Inject

@AppScope
class TrackerInteractor @Inject constructor(
    private val timer: Timer
) {
    fun startTimer() {
        timer.startTimer()
    }

    fun stopTimer() {
        timer.stopTimer()
    }

    fun resumeTimer() {
        timer.resumeTimer()
    }

    fun pauseTimer() {
        timer.saveTimer()
    }

    fun getProgress(): LiveData<Long> {
        return timer.progress
    }

    fun getProgressMax(): LiveData<Long> {
        return timer.progressMax
    }

    fun getState(): LiveData<TimerState> {
        return timer.state
    }

    fun getProgressRemaining(): LiveData<Long> {
        return timer.progressRemaining
    }

    fun getTimerLength(): Long {
        return timer.getTimerLength()
    }

    fun getEndTime(): LiveData<Long> {
        return timer.endTime
    }

    fun isFirstLaunch(): Boolean {
        return timer.isFirstLaunch()
    }
}