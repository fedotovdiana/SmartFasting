package com.itis.group11801.fedotova.smartfasting.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.managers.AlarmsManager
import com.itis.group11801.fedotova.smartfasting.app.managers.NotificationsManager
import com.itis.group11801.fedotova.smartfasting.app.managers.PreferenceManager
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
        alarmsManager.removeAlarm()
        preferenceManager.setAlarmSetTime(0)
        preferenceManager.resetRemainingSeconds()
        notificationsManager.hideTimerNotification()
    }
}
