package com.itis.group11801.fedotova.smartfasting.app.managers

import android.content.SharedPreferences
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

    fun resetRemainingSeconds() {
        setRemainingSeconds(getTimerLength())
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
        return preferences.getString(DAY_DRINK_VOLUME_ID, "2000")?.toInt() ?: 0
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

    fun isFirstLaunch(): Boolean {
        return preferences.getBoolean(IS_FIRST_LAUNCH_ID, true)
    }

    fun setIsFirstLaunch() {
        if (isFirstLaunch()) {
            with(preferences.edit()) {
                putBoolean(IS_FIRST_LAUNCH_ID, false)
                apply()
            }
        }
    }

    companion object {
        const val REMAINING_SECONDS_ID = "smartfasting.timer.seconds_remaining"
        const val ALARM_SET_TIME_ID = "smartfasting.timer.backgrounded_time"
        const val DIET_PLAN_ID = "smartfasting.diet_plan"
        const val DAY_DRINK_VOLUME_ID = "smartfasting.volume"
        const val DRINK_VOLUME_ID = "smartfasting.drink_volume"
        const val DATE_ID = "smartfasting.date"
        const val TIMER_LENGTH_ID = "smartfasting.timer_length"
        const val IS_FIRST_LAUNCH_ID = "smartfasting.is_first_launch"
        const val FIRST_DIET_TIME_ID = 60L
        const val SECOND_DIET_TIME_ID = 50400L
        const val THIRD_DIET_TIME_ID = 57600L
        const val FOURTH_DIET_TIME_ID = 72000L
    }
}
