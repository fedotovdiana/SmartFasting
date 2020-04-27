package com.itis.group11801.fedotova.smartfasting.app.utils.tracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.*
import javax.inject.Inject

@AppScope
class TimerNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var alarmsManager: AlarmsManager

    @Inject
    lateinit var notificationsManager: NotificationsManager

    override fun onReceive(context: Context, intent: Intent) {
        AppInjector.injectTimerNotificationReceiver(this)
        when (intent.action) {
            ACTION_STOP -> {
                alarmsManager.removeAlarm()
                preferenceManager.setTimerState(STOPPED)
                preferenceManager.setAlarmSetTime(0)
                notificationsManager.hideTimerNotification()
            }
            ACTION_PAUSE -> {
                var secondsRemaining = preferenceManager.getRemainingSeconds()
                val alarmSetTime = preferenceManager.getAlarmSetTime()
                val nowSeconds = alarmsManager.nowSeconds
                secondsRemaining -= nowSeconds - alarmSetTime

                alarmsManager.removeAlarm()
                preferenceManager.setRemainingSeconds(secondsRemaining)
                preferenceManager.setTimerState(PAUSED)
                notificationsManager.showTimerPaused()
            }
            ACTION_RESUME -> {
                val secondsRemaining = preferenceManager.getRemainingSeconds()
                val wakeUpTime = alarmsManager.setAlarm(alarmsManager.nowSeconds, secondsRemaining)
                preferenceManager.setTimerState(RUNNING)
                notificationsManager.showTimerRunning(wakeUpTime)
            }
            ACTION_START -> {
                val minutesRemaining = preferenceManager.getNewestTimerLength()
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = alarmsManager.setAlarm(alarmsManager.nowSeconds, secondsRemaining)
                preferenceManager.setTimerState(RUNNING)
                preferenceManager.setRemainingSeconds(secondsRemaining)
                notificationsManager.showTimerRunning(wakeUpTime)
            }
        }
    }
}
