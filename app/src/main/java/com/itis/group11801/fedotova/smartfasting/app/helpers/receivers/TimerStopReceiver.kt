package com.itis.group11801.fedotova.smartfasting.app.helpers.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.helpers.services.TimerStopJobIntentService

class TimerStopReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, TimerStopJobIntentService::class.java)
        TimerStopJobIntentService.enqueueWork(context, serviceIntent)
    }
}
