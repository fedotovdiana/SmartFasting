package com.itis.group11801.fedotova.smartfasting.app.utils.tracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import javax.inject.Inject

@AppScope
class TimerExpiredReceiver : BroadcastReceiver() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var notificationsManager: NotificationsManager

    override fun onReceive(context: Context, intent: Intent) {
        AppInjector.injectTimerExpiredReceiver(this)
        preferenceManager.setAlarmSetTime(0)
        notificationsManager.showTimerExpired()
    }
}
