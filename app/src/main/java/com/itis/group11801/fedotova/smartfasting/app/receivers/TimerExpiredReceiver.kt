package com.itis.group11801.fedotova.smartfasting.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.Timer
import javax.inject.Inject

@AppScope
class TimerExpiredReceiver : BroadcastReceiver() {

    @Inject
    lateinit var timer: Timer

    override fun onReceive(context: Context, intent: Intent) {
        AppInjector.injectTimerExpiredReceiver(this)
        timer.onTimerExpiredReceive()
    }
}
