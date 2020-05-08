package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.AlarmsManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.NotificationsManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.RUNNING
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.STOPPED
import kotlinx.coroutines.*
import javax.inject.Inject

@AppScope
class Tracker @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val alarmsManager: AlarmsManager,
    private val notificationsManager: NotificationsManager
) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private lateinit var timer: Job
    private var count = 0
    private var countStop = 0

    private var lengthSeconds: Long = 0
    private var remainingSeconds: Long = 0

    private var _progress: MutableLiveData<Int> = MutableLiveData(0)
    val progress: LiveData<Int>
        get() = _progress

    private var _progressMax: MutableLiveData<Int> = MutableLiveData(0)
    val progressMax: LiveData<Int>
        get() = _progressMax

    private var _text = MutableLiveData(0)
    val text: LiveData<Int>
        get() = _text

    private var _state = MutableLiveData(STOPPED)
    val state: LiveData<TimerState>
        get() = _state

    private suspend fun startCoroutineTimer(repeatMillis: Long = 0, remainingSec: Long) {
        for (ind: Long in remainingSec - 1 downTo 0) {
            Log.d(TAG, "Background - tick $ind")
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Main thread - tick")
                _text.value = ind.toInt()
            }
            delay(repeatMillis)
        }
        withContext(Dispatchers.Main) {
            stopTimer()
        }
    }

    fun startTimer() {
        timer = scope.launch {
            startCoroutineTimer(1000, remainingSeconds)
        }
        Log.e("AS", count.toString())
        _state.value = RUNNING
        count++
    }

    fun stopTimer() {
        timer.cancel()
        _state.value = STOPPED
        Log.e("AC", countStop.toString())
        countStop = count

//        _progresMax.value = preferenceManager.getTimerLength()

        remainingSeconds = lengthSeconds
        preferenceManager.setRemainingSeconds(remainingSeconds)
    }

    fun resumeTimer() {

        lengthSeconds = preferenceManager.getTimerLength()

        remainingSeconds = preferenceManager.getRemainingSeconds() -
                (alarmsManager.nowSeconds - preferenceManager.getAlarmSetTime())

        if (remainingSeconds <= 0) {
            stopTimer()
            count++
        } else if (_state.value == RUNNING && count == countStop) {
            startTimer()
        }
        removeAlarm()

//        if (_state.value == RUNNING && (count == countStop)) {
//            startTimer()
//        } else {
//            count++
//        }
    }

    fun pauseTimer() {
        if (_state.value == RUNNING && (count == 1 + countStop)) {
            timer.cancel()
            Log.e("PAUSE", countStop.toString())
            countStop = count

            val wakeUpTime = setAlarm(alarmsManager.nowSeconds, remainingSeconds)
            notificationsManager.showTimerRunning(wakeUpTime)
        }
        preferenceManager.setRemainingSeconds(remainingSeconds)
    }

    private fun setAlarm(nowSeconds: Long, remainingSeconds: Long): Long {
        val wakeUpTime = alarmsManager.setAlarm(nowSeconds, remainingSeconds)
        preferenceManager.setAlarmSetTime(nowSeconds)
        return wakeUpTime
    }

    private fun removeAlarm() {
        alarmsManager.removeAlarm()
        preferenceManager.setAlarmSetTime(0)
        notificationsManager.hideTimerNotification()
    }
}
