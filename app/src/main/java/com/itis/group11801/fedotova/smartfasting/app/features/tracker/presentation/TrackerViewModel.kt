package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.AlarmsManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.*
import java.util.*
import javax.inject.Inject

@ScreenScope
class TrackerViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val alarmsManager: AlarmsManager
) : ViewModel() {

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var secondsRemaining: Long = 0
    private val nowSeconds: Long
        get() = Calendar.getInstance().timeInMillis / 1000

    private var _progress: MutableLiveData<Int> = MutableLiveData(0)
    val progress: LiveData<Int>
        get() = _progress

    private var _progressMax: MutableLiveData<Int> = MutableLiveData(0)
    val progressMax: LiveData<Int>
        get() = _progressMax

    private var _progressText: MutableLiveData<String> = MutableLiveData("")
    val progressText: LiveData<String>
        get() = _progressText

    private var _timerState: MutableLiveData<TimerState> = MutableLiveData(STOPPED)
    val timerState: LiveData<TimerState>
        get() = _timerState

    private fun setAlarm(nowSeconds: Long, secondsRemaining: Long) {
        alarmsManager.setAlarm(nowSeconds, secondsRemaining)
        preferenceManager.setAlarmSetTime(nowSeconds)
    }

    private fun removeAlarm() {
        alarmsManager.removeAlarm()
        preferenceManager.setAlarmSetTime(0)
    }

    fun initTimer() {
        _timerState.value = preferenceManager.getTimerState()

        if (_timerState.value == STOPPED)
            setCurrentTimerLength()
        else setLastTimerLength()

        secondsRemaining =
            if (_timerState.value == RUNNING || _timerState.value == PAUSED) {
                preferenceManager.getSecondsRemaining()
            } else timerLengthSeconds

        val alarmSetTime = preferenceManager.getAlarmSetTime()
        if (alarmSetTime > 0)
            secondsRemaining -= nowSeconds - alarmSetTime

        if (secondsRemaining <= 0)
            onTimerFinished()
        else if (_timerState.value == RUNNING)
            startTimer()

        updateCountdownUI()
        removeAlarm()
    }

    fun saveTimer() {
        if (_timerState.value == RUNNING) {
            timer.cancel()
            setAlarm(nowSeconds, secondsRemaining)
        }
        preferenceManager.setCurrentTimerLengthSeconds(timerLengthSeconds)
        preferenceManager.setSecondsRemaining(secondsRemaining)
        preferenceManager.setTimerState(timerState.value!!)
    }

    private fun onTimerFinished() {
        _timerState.value = STOPPED
        setCurrentTimerLength()
        _progress.value = 0
        preferenceManager.setSecondsRemaining(timerLengthSeconds)
        secondsRemaining = timerLengthSeconds
        updateCountdownUI()
    }

    private fun startTimer() {
        _timerState.value = RUNNING

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setCurrentTimerLength() {
        val lengthInMinutes = preferenceManager.getLastTimerLength()
        timerLengthSeconds = (lengthInMinutes * 60L)
        _progressMax.value = timerLengthSeconds.toInt()
    }

    private fun setLastTimerLength() {
        timerLengthSeconds = preferenceManager.getCurrentTimerLengthSeconds()
        _progressMax.value = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        _progressText.value =
            "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        _progress.value = (timerLengthSeconds - secondsRemaining).toInt()
    }

    fun onStartButtonPressed() {
        startTimer()
    }

    fun onPauseButtonPressed() {
        timer.cancel()
        _timerState.value = PAUSED
    }

    fun onStopButtonPressed() {
        timer.cancel()
        onTimerFinished()
    }
}
