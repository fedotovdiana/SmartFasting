package com.itis.group11801.fedotova.smartfasting.app.helpers.managers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.helpers.receivers.TimerExpiredReceiver
import javax.inject.Inject

@AppScope
class AlarmsManager @Inject constructor(
    val context: Context
) {
    fun setAlarm(nowSeconds: Long, secondsRemaining: Long): Long {
        val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
        val intent = Intent(context, TimerExpiredReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
        return wakeUpTime
    }

    fun removeAlarm() {
        val intent = Intent(context, TimerExpiredReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}
