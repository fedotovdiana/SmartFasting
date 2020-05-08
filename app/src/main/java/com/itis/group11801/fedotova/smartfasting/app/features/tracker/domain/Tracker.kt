package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.*
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
    private var progressRemain: Long = 0

    private var _progress: MutableLiveData<Long> = MutableLiveData(0)
    val progress: LiveData<Long>
        get() = _progress

    private var _progressMax: MutableLiveData<Long> = MutableLiveData(0)
    val progressMax: LiveData<Long>
        get() = _progressMax

//    private var _text = MutableLiveData(0)
//    val text: LiveData<Int>
//        get() = _text

    private var _state = MutableLiveData(STOPPED)
    val state: LiveData<TimerState>
        get() = _state

    private suspend fun startCoroutineTimer() {
        for (ind: Long in (progressRemain - 1) downTo 0) {
            Log.d(TAG, "Background - tick $ind")
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Main thread - tick")
                progressRemain--
                _progress.value = _progressMax.value?.minus(progressRemain)
            }
            delay(SECOND)
        }
        withContext(Dispatchers.Main) {
            stopTimer()
        }
    }

    fun startTimer() {
        timer = scope.launch {
            startCoroutineTimer()
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

        preferenceManager.getTimerLength().also {
            progressRemain = it
            _progressMax.value = it
            preferenceManager.setRemainingSeconds(it)
        }
        _progress.value = _progressMax.value?.minus(progressRemain)
    }

    fun resumeTimer() {

        _progressMax.value = preferenceManager.getTimerLength()
        progressRemain = preferenceManager.getRemainingSeconds()

        if (preferenceManager.getRemainingSeconds() < preferenceManager.getTimerLength()) {

            progressRemain -= (alarmsManager.nowSeconds - preferenceManager.getAlarmSetTime())

            if (progressRemain <= 0) {
                stopTimer()
                count++
            } else {
                startTimer()
            }
            removeAlarm()
        } else count++
    }

    fun pauseTimer() {
        if (_state.value == RUNNING && (count == 1 + countStop)) {
            timer.cancel()

            val wakeUpTime = setAlarm(alarmsManager.nowSeconds, progressRemain)
            notificationsManager.showTimerRunning(wakeUpTime)
            preferenceManager.setRemainingSeconds(progressRemain)

            Log.e("PAUSE", countStop.toString())
            countStop = count
        }
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
