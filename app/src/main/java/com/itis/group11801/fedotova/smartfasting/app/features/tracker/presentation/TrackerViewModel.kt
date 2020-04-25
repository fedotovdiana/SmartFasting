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
    private var remainingSeconds: Long = 0
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

    //блок работы с таймером
    fun initTimer() {
        _timerState.value = preferenceManager.getTimerState()

        if (_timerState.value == STOPPED) {
            setNewestTimerLength()
        } else {
            setCurrentTimerLength()
        }

        //сколько секунд осталось
        remainingSeconds =
            if (_timerState.value == RUNNING || _timerState.value == PAUSED)
                preferenceManager.getSecondsRemaining()
            else timerLengthSeconds

        //пересчитываем оставшиеся секунды
        val alarmSetTime = preferenceManager.getAlarmSetTime()
        if (alarmSetTime > 0) remainingSeconds -= nowSeconds - alarmSetTime

        //если время вышло, обнуляем таймер; иначе запускаем
        if (remainingSeconds <= 0) {
            stopTimer()
        } else if (_timerState.value == RUNNING) {
            startTimer()
        }
        updateProgress()
        removeAlarm()
    }

    fun startTimer() {
        _timerState.value = RUNNING

        timer = object : CountDownTimer(remainingSeconds * 1000, 1000) {

            override fun onFinish() = stopTimer()

            override fun onTick(millisUntilFinished: Long) {
                remainingSeconds = millisUntilFinished / 1000
                updateProgress()
            }
        }.start()
    }

    fun pauseTimer() {
        _timerState.value = PAUSED

        timer.cancel()
    }

    fun stopTimer() {
        _timerState.value = STOPPED
        timer.cancel()
        setNewestTimerLength()
        _progress.value = 0
        preferenceManager.setSecondsRemaining(timerLengthSeconds)
        remainingSeconds = timerLengthSeconds
        updateProgress()
    }

    fun saveTimer() {
        if (_timerState.value == RUNNING) {
            timer.cancel()
            setAlarm(nowSeconds, remainingSeconds)
        }
        preferenceManager.setCurrentTimerLengthSeconds(timerLengthSeconds)
        preferenceManager.setSecondsRemaining(remainingSeconds)
        preferenceManager.setTimerState(timerState.value!!)
    }

    //блок обновления preferences
    private fun setAlarm(nowSeconds: Long, secondsRemaining: Long) {
        alarmsManager.setAlarm(nowSeconds, secondsRemaining)
        preferenceManager.setAlarmSetTime(nowSeconds)
    }

    private fun removeAlarm() {
        alarmsManager.removeAlarm()
        preferenceManager.setAlarmSetTime(0)
    }

    private fun setNewestTimerLength() {
        val lengthInMinutes = preferenceManager.getNewestTimerLength()
        timerLengthSeconds = (lengthInMinutes * 60L)
        _progressMax.value = timerLengthSeconds.toInt()
    }

    private fun setCurrentTimerLength() {
        timerLengthSeconds = preferenceManager.getCurrentTimerLengthSeconds()
        _progressMax.value = timerLengthSeconds.toInt()
    }

    private fun updateProgress() {
        //минуты на часах
        val minutesUntilFinished = remainingSeconds / 60
        //секунды на часах
        val secondsInMinuteUntilFinished = remainingSeconds - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        _progressText.value =
            "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        _progress.value = (timerLengthSeconds - remainingSeconds).toInt()
    }
}
