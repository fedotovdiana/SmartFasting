package com.itis.group11801.fedotova.smartfasting.app.managers

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.MainActivity
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.receivers.TimerNotificationReceiver
import com.itis.group11801.fedotova.smartfasting.app.utils.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AppScope
class NotificationsManager @Inject constructor(
    val context: Context
) {
    fun showTimerRunning(wakeUpTime: Long) {
        val stopIntent = Intent(context, TimerNotificationReceiver::class.java)
        val stopPendingIntent = PendingIntent.getBroadcast(
            context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)
        val notification = getBasicNotificationBuilder()
            .setContentTitle(TIMER_RUNNING)
            .setContentText("End at: ${df.format(Date(wakeUpTime))}")
            .setContentIntent(getResultIntent())
            .setOngoing(true)
            .addAction(R.drawable.ic_stop, TIMER_BTN_STOP, stopPendingIntent).build()

        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.createNotificationChannel()
        nManager.notify(TIMER_ID, notification)
    }

    fun showTimerExpired() {
        val notification = getBasicNotificationBuilder()
            .setContentTitle(TIMER_EXPIRED)
            .setContentIntent(getResultIntent())
            .build()

        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.createNotificationChannel()
        nManager.notify(TIMER_ID, notification)
    }

    fun hideTimerNotification() {
        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.cancel(TIMER_ID)
    }

    private fun getBasicNotificationBuilder() =
        NotificationCompat.Builder(context,
                CHANNEL_ID_TIMER
            )
            .setSmallIcon(R.drawable.ic_timer)
            .setAutoCancel(true)
            .setDefaults(0)

    private fun getResultIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(
            INTENT_EXTRA,
            OPEN_TRACKER
        )
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    @TargetApi(26)
    private fun NotificationManager.createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID_TIMER,
                    CHANNEL_NAME_TIMER,
                    NotificationManager.IMPORTANCE_LOW
                )
            )
        }
    }
}
