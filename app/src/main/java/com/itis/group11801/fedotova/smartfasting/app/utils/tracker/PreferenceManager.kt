package com.itis.group11801.fedotova.smartfasting.app.utils.tracker

import android.content.SharedPreferences
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import javax.inject.Inject

@AppScope
class PreferenceManager @Inject constructor(
    private val preferences: SharedPreferences
) {
    //свеженастроенное время в минутах
    fun getNewestTimerLength(): Int {
        return when (getDietID()) {
            0 -> FIRST_DIET_TIME_ID
            1 -> SECOND_DIET_TIME_ID
            2 -> THIRD_DIET_TIME_ID
            else -> FOURTH_DIET_TIME_ID
        }
    }

    //время незавершенного таймера
    fun getCurrentTimerLengthSeconds(): Long {
        return preferences.getLong(CURRENT_TIMER_LENGTH_ID, 0)
    }

    fun setCurrentTimerLengthSeconds(seconds: Long) {
        with(preferences.edit()) {
            putLong(CURRENT_TIMER_LENGTH_ID, seconds)
            apply()
        }
    }

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

    fun getRemainingSeconds(): Long {
        return preferences.getLong(REMAINING_SECONDS_ID, 0)
    }

    fun setRemainingSeconds(seconds: Long) {
        with(preferences.edit()) {
            putLong(REMAINING_SECONDS_ID, seconds)
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

    private fun getDietID(): Int {
        return preferences.getInt(DIET_PLAN_ID, 0)
    }

    fun setDietID(id: Int) {
        with(preferences.edit()) {
            putInt(DIET_PLAN_ID, id)
            apply()
        }
    }

    fun getDayWaterVolume(): Int {
//        return preferences.getInt(DAY_WATER_VOLUME, 0)
        return 2000
    }

    fun getWaterVolume(): Int {
        return preferences.getInt(WATER_VOLUME_ID, 0)
    }

    fun setWaterWVolume(volume: Int) {
        with(preferences.edit()) {
            putInt(WATER_VOLUME_ID, volume)
            apply()
        }
    }

    fun getDate(): String? {
        return preferences.getString(DATE_ID, "")
    }

    fun setDate(date: String) {
        with(preferences.edit()) {
            putString(DATE_ID, date)
            apply()
        }
    }
}
