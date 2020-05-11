package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.AlarmsManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.NotificationsManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.SECOND
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.RUNNING
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.STOPPED
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@AppScope
class Tracker @Inject constructor(
    private val repository: TrackerRepository,
    private val alarmsManager: AlarmsManager,
    private val notificationsManager: NotificationsManager
) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private lateinit var timer: Job
    private var progressRemain: Long = 0

    private var _progress: MutableLiveData<Long> = MutableLiveData(0)
    val progress: LiveData<Long>
        get() = _progress

    private var _progressMax: MutableLiveData<Long> = MutableLiveData(0)
    val progressMax: LiveData<Long>
        get() = _progressMax

    private var _progressTime: MutableLiveData<Long> = MutableLiveData(0)
    val progressTime: LiveData<Long>
        get() = _progressTime

    private var _state = MutableLiveData(STOPPED)
    val state: LiveData<TimerState>
        get() = _state

    private var _startTime: MutableLiveData<Long> = MutableLiveData(0)
    val startTime: LiveData<Long>
        get() = _startTime

    private val nowSeconds: Long
        get() = Calendar.getInstance().timeInMillis / 1000

    fun resumeTimer() {
        progressRemain = repository.getRemainingSeconds()
        _progressTime.value = progressRemain
        _progressMax.value = repository.getTimerLength()

        if (repository.getRemainingSeconds() < repository.getTimerLength()) {
            progressRemain -= (nowSeconds - repository.getAlarmSetTime())
            if (progressRemain <= 0) stopTimer() else startTimer()
            removeAlarm()
        }
    }

    fun startTimer() {
        _state.value = RUNNING
        _startTime.value = Date().time + progressRemain * 1000
        timer = scope.launch {
            startCoroutineTimer()
        }
    }

    fun stopTimer() {
        _state.value = STOPPED
        timer.cancel()

        repository.getTimerLength().also {
            progressRemain = it
            _progressMax.value = it
            _progressTime.value = it
            repository.setRemainingSeconds(it)
        }
        _progress.value = _progressMax.value?.minus(progressRemain)
    }

    fun cancelTimer() {
        if (_state.value == RUNNING) {
            timer.cancel()
            setAlarm()
        }
    }

    private suspend fun startCoroutineTimer() {
        for (ind: Long in (progressRemain - 1) downTo 0) {
            Log.d("TAG", "Background - tick $ind")
            withContext(Dispatchers.Main) {
                progressRemain--
                _progressTime.value = progressRemain
                _progress.value = _progressMax.value?.minus(progressRemain)
            }
            delay(SECOND)
        }
        withContext(Dispatchers.Main) {
            stopTimer()
        }
    }

    private fun setAlarm() {
        val wakeUpTime = alarmsManager.setAlarm(nowSeconds, progressRemain)
        repository.setAlarmSetTime(nowSeconds)
        repository.setRemainingSeconds(progressRemain)
        notificationsManager.showTimerRunning(wakeUpTime)
    }

    private fun removeAlarm() {
        alarmsManager.removeAlarm()
        repository.setAlarmSetTime(0)
        notificationsManager.hideTimerNotification()
    }

    fun getTimerLength(): Long {
        return repository.getTimerLength()
    }
}
