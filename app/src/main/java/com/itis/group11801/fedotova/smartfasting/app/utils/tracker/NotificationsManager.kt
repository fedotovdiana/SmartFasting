package com.itis.group11801.fedotova.smartfasting.app.utils.tracker

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.MainActivity
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AppScope
class NotificationsManager @Inject constructor(
    val context: Context
) {
    fun showTimerRunning(wakeUpTime: Long) {
        val stopIntent = Intent(context, TimerNotificationReceiver::class.java)
        stopIntent.action = ACTION_STOP
        val stopPendingIntent = PendingIntent.getBroadcast(
            context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pauseIntent = Intent(context, TimerNotificationReceiver::class.java)
        pauseIntent.action = ACTION_PAUSE
        val pausePendingIntent = PendingIntent.getBroadcast(
            context, 0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

        val nBuilder = getBasicNotificationBuilder(CHANNEL_ID_TIMER)
        nBuilder.setContentTitle("Timer is Running.")
            .setContentText("End: ${df.format(Date(wakeUpTime))}")
            .setContentIntent(getPendingIntentWithStack(MainActivity::class.java))
            .setOngoing(true)
            .addAction(R.drawable.ic_stop, "Stop", stopPendingIntent)
            .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)

        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER)
        nManager.notify(TIMER_ID, nBuilder.build())
    }

    fun showTimerPaused() {
        val resumeIntent = Intent(context, TimerNotificationReceiver::class.java)
        resumeIntent.action = ACTION_RESUME
        val resumePendingIntent = PendingIntent.getBroadcast(
            context, 0, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val nBuilder = getBasicNotificationBuilder(CHANNEL_ID_TIMER)
        nBuilder.setContentTitle("Timer is paused")
            .setContentText("Resume?")
            .setContentIntent(getPendingIntentWithStack(MainActivity::class.java))
            .setOngoing(true)
            .addAction(R.drawable.ic_play_arrow, "Resume", resumePendingIntent)

        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER)
        nManager.notify(TIMER_ID, nBuilder.build())
    }

    fun showTimerExpired() {
        val startIntent = Intent(context, TimerNotificationReceiver::class.java)
        startIntent.action = ACTION_START
        val startPendingIntent = PendingIntent.getBroadcast(
            context,
            0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val nBuilder = getBasicNotificationBuilder(CHANNEL_ID_TIMER)
        nBuilder.setContentTitle("Timer Expired!")
            .setContentText("Start again?")
            .setContentIntent(getPendingIntentWithStack(MainActivity::class.java))
            .addAction(R.drawable.ic_play_arrow, "Start", startPendingIntent)

        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER)
        nManager.notify(TIMER_ID, nBuilder.build())
    }

    fun hideTimerNotification() {
        val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nManager.cancel(TIMER_ID)
    }

    private fun getBasicNotificationBuilder(channelID: String) =
        NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_timer)
            .setAutoCancel(true)
            .setDefaults(0)

    private fun <T> getPendingIntentWithStack(
        javaClass: Class<T>
    ): PendingIntent {
        val resultIntent = Intent(context, javaClass)
        resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(javaClass)
        stackBuilder.addNextIntent(resultIntent)

        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    @TargetApi(26)
    private fun NotificationManager.createNotificationChannel(
        channelID: String,
        channelName: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nChannel =
                NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_LOW)
            this.createNotificationChannel(nChannel)
        }
    }
}
