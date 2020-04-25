package com.itis.group11801.fedotova.smartfasting.app.utils.tracker

import android.content.SharedPreferences
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import javax.inject.Inject

@AppScope
class PreferenceManager @Inject constructor(
    private val preferences: SharedPreferences
) {

    //свеженастроенное время
    fun getNewestTimerLength(): Int {
        //return preferences.getInt(LAST_TIMER_LENGTH_ID, 10)
        return 1
    }

    //время текущего таймера
    fun getCurrentTimerLengthSeconds(): Long {
        return preferences.getLong(CURRENT_TIMER_LENGTH_ID, 0)
    }

    fun setCurrentTimerLengthSeconds(seconds: Long) {
        with(preferences.edit()) {
            putLong(CURRENT_TIMER_LENGTH_ID, seconds)
            apply()
        }
    }

    //состояние таймера
    fun getTimerState(): TimerState {
        val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
        return TimerState.values()[ordinal]
    }

    fun setTimerState(state: TimerState) {
        val ordinal = state.ordinal
        with(preferences.edit()) {
            putInt(TIMER_STATE_ID, ordinal)
            apply()
        }
    }

    //оставшиеся секунды
    fun getSecondsRemaining(): Long {
        return preferences.getLong(SECONDS_REMAINING_ID, 0)
    }

    fun setSecondsRemaining(seconds: Long) {
        with(preferences.edit()) {
            putLong(SECONDS_REMAINING_ID, seconds)
            apply()
        }
    }

    //время установки таймера (начала отсчета)
    fun getAlarmSetTime(): Long {
        return preferences.getLong(ALARM_SET_TIME_ID, 0)
    }

    fun setAlarmSetTime(time: Long) {
        with(preferences.edit()) {
            putLong(ALARM_SET_TIME_ID, time)
            apply()
        }
    }
}
