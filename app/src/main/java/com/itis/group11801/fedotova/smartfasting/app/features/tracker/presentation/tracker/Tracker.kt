package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import com.itis.group11801.fedotova.smartfasting.app.helpers.managers.AlarmsManager
import com.itis.group11801.fedotova.smartfasting.app.helpers.managers.NotificationsManager
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@AppScope
class Tracker @Inject constructor(
    private val interactor: TrackerInteractor,
    private val alarmsManager: AlarmsManager,
    private val notificationsManager: NotificationsManager
) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private lateinit var timer: Job
    private var remTime: Long = 0
    private var maxTime: Long = 0

    private var _progress: MutableLiveData<Long> = MutableLiveData(0)
    val progress: LiveData<Long>
        get() = _progress

    private var _progressMax: MutableLiveData<Long> = MutableLiveData(0)
    val progressMax: LiveData<Long>
        get() = _progressMax

    private var _progressRemaining: MutableLiveData<Long> = MutableLiveData(0)
    val progressRemaining: LiveData<Long>
        get() = _progressRemaining

    private var _state = MutableLiveData(TrackerState.STOPPED)
    val state: LiveData<TrackerState>
        get() = _state

    private var _endTime: MutableLiveData<Long> = MutableLiveData(0)
    val endTime: LiveData<Long>
        get() = _endTime

    private val nowSeconds: Long
        get() = Calendar.getInstance().timeInMillis / 1000

    fun resumeTimer() {
        remTime = interactor.getRemainingSeconds()
        maxTime = interactor.getTimerLength()
        if (remTime < maxTime) {
            remTime -= (nowSeconds - interactor.getAlarmSetTime())
            if (remTime > 0) {
                startTimer()
            } else {
                remTime = maxTime
                update(TrackerState.STOPPED)
            }
            removeAlarm()
        } else {
            update(TrackerState.STOPPED)
        }
    }

    fun startTimer() {
        update(TrackerState.RUNNING)
        timer = scope.launch {
            for (ind: Long in (remTime - 1) downTo 0) {
                withContext(Dispatchers.Main) {
                    remTime--
                    _progressRemaining.value = remTime
                    _progress.value = maxTime - remTime
                }
                delay(SECOND)
            }
            withContext(Dispatchers.Main) {
                stopTimer()
            }
        }
    }

    fun stopTimer() {
        timer.cancel()
        saveNote(maxTime - remTime)
        remTime = maxTime
        interactor.setRemainingSeconds(maxTime)
        update(TrackerState.STOPPED)
    }

    fun saveTimer() {
        if (_state.value == TrackerState.RUNNING) {
            timer.cancel()
            interactor.setRemainingSeconds(remTime)
            setAlarm()
        }
    }

    fun onTimerExpiredReceive() {
        notificationsManager.showTimerExpired()
        saveNote(interactor.getTimerLength())
        removeAlarm()
        interactor.resetRemainingSeconds()
    }

    fun onTimerStopReceive() {
        notificationsManager.hideTimerNotification()
        val time = interactor.getTimerLength() -
                (interactor.getRemainingSeconds() - (nowSeconds - interactor.getAlarmSetTime()))
        saveNote(time)
        removeAlarm()
        interactor.resetRemainingSeconds()
    }

    fun getTimerLength() = interactor.getTimerLength()

    fun isDietAdded() = interactor.isDietAdded()

    private fun setAlarm() {
        val wakeUpTime = alarmsManager.setAlarm(nowSeconds, remTime)
        interactor.setAlarmSetTime(nowSeconds)
        interactor.setRemainingSeconds(remTime)
        notificationsManager.showTimerRunning(wakeUpTime)
    }

    private fun removeAlarm() {
        notificationsManager.hideTimerNotification()
        alarmsManager.removeAlarm()
        interactor.setAlarmSetTime(0)
    }

    private fun saveNote(time: Long) {
        scope.launch { interactor.saveTrackerNote(TrackerNote(time, Date())) }
    }

    private fun update(state: TrackerState) {
        _progressRemaining.value = remTime
        _progressMax.value = maxTime
        _progress.value = maxTime - remTime
        _state.value = state
        _endTime.value = Date().time + remTime * 1000
    }

    companion object {
        const val SECOND = 1000L
    }
}
