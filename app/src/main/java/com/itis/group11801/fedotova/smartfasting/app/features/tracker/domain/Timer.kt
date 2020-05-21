package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import com.itis.group11801.fedotova.smartfasting.app.managers.AlarmsManager
import com.itis.group11801.fedotova.smartfasting.app.managers.NotificationsManager
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@AppScope
class Timer @Inject constructor(
    private val repository: TrackerRepository,
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

    private var _state = MutableLiveData(TimerState.STOPPED)
    val state: LiveData<TimerState>
        get() = _state

    private var _endTime: MutableLiveData<Long> = MutableLiveData(0)
    val endTime: LiveData<Long>
        get() = _endTime

    private val nowSeconds: Long
        get() = Calendar.getInstance().timeInMillis / 1000

    fun resumeTimer() {
        remTime = repository.getRemainingSeconds()
        maxTime = repository.getTimerLength()
        if (remTime < maxTime) {
            remTime -= (nowSeconds - repository.getAlarmSetTime())
            if (remTime > 0) {
                startTimer()
            } else {
                remTime = maxTime
                updateUI(TimerState.STOPPED)
            }
            removeAlarm()
        } else {
            updateUI(TimerState.STOPPED)
        }
    }

    fun startTimer() {
        updateUI(TimerState.RUNNING)
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
        saveToDb(maxTime - remTime)
        remTime = maxTime
        repository.setRemainingSeconds(maxTime)
        updateUI(TimerState.STOPPED)
    }

    fun saveTimer() {
        if (_state.value == TimerState.RUNNING) {
            timer.cancel()
            repository.setRemainingSeconds(remTime)
            setAlarm()
        }
    }

    fun onTimerExpiredReceive() {
        notificationsManager.showTimerExpired()
        saveToDb(repository.getTimerLength())
        removeAlarm()
        repository.resetRemainingSeconds()
    }

    fun onTimerStopReceive() {
        notificationsManager.hideTimerNotification()
        val time = repository.getTimerLength() -
                (repository.getRemainingSeconds() - (nowSeconds - repository.getAlarmSetTime()))
        saveToDb(time)
        removeAlarm()
        repository.resetRemainingSeconds()
    }

    fun getTimerLength() = repository.getTimerLength()

    fun isFirstLaunch() = repository.isFirstLaunch()


    private fun setAlarm() {
        val wakeUpTime = alarmsManager.setAlarm(nowSeconds, remTime)
        repository.setAlarmSetTime(nowSeconds)
        repository.setRemainingSeconds(remTime)
        notificationsManager.showTimerRunning(wakeUpTime)
    }

    private fun removeAlarm() {
        alarmsManager.removeAlarm()
        repository.setAlarmSetTime(0)
    }

    private fun saveToDb(time: Long) {
        Log.e("TIME", time.toString())
        scope.launch { repository.saveTrackerNote(TrackerNote(time, Date())) }
    }

    private fun updateUI(state: TimerState) {
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
