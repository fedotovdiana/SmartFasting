package com.itis.group11801.fedotova.smartfasting.app.utils.tracker

import android.content.SharedPreferences
import android.util.Log
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import javax.inject.Inject

@AppScope
class PreferenceManager @Inject constructor(
    private val preferences: SharedPreferences
) {
    fun getTimerLength(): Long {
        return preferences.getLong(TIMER_LENGTH_ID, 0)
    }

    private fun setTimerLength(time: Long) {
        with(preferences.edit()) {
            putLong(TIMER_LENGTH_ID, time)
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

    fun getAlarmSetTime(): Long {
        return preferences.getLong(ALARM_SET_TIME_ID, 0)
    }

    fun setAlarmSetTime(time: Long) {
        with(preferences.edit()) {
            putLong(ALARM_SET_TIME_ID, time)
            apply()
        }
    }

    fun setDiet(id: Int) {
        with(preferences.edit()) {
            when (id) {
                0 -> {
                    setTimerLength(FIRST_DIET_TIME_ID)
                    setRemainingSeconds(FIRST_DIET_TIME_ID)
                }
                1 -> {
                    setTimerLength(SECOND_DIET_TIME_ID)
                    setRemainingSeconds(SECOND_DIET_TIME_ID)
                }
                2 -> {
                    setTimerLength(THIRD_DIET_TIME_ID)
                    setRemainingSeconds(THIRD_DIET_TIME_ID)
                }
                else -> {
                    setTimerLength(FOURTH_DIET_TIME_ID)
                    setRemainingSeconds(FOURTH_DIET_TIME_ID)
                }
            }
            apply()
        }
    }

    fun getDayWaterVolume(): Int {
        Log.e("RRR", preferences.getInt(DAY_DRINK_VOLUME_ID, 12).toString())
        return preferences.getInt(DAY_DRINK_VOLUME_ID, 0)
    }

    fun getDrinkVolume(): Int {
        return preferences.getInt(DRINK_VOLUME_ID, 0)
    }

    fun setDrinkVolume(volume: Int) {
        with(preferences.edit()) {
            putInt(DRINK_VOLUME_ID, volume)
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
