package com.itis.group11801.fedotova.smartfasting.app.helpers.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.helpers.services.TimerExpiredJobIntentService

@AppScope
class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, TimerExpiredJobIntentService::class.java)
        TimerExpiredJobIntentService.enqueueWork(context, serviceIntent)
    }
}
