package com.itis.group11801.fedotova.smartfasting.app.helpers.services

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker.Tracker
import javax.inject.Inject

class TimerExpiredJobIntentService : JobIntentService() {

    @Inject
    lateinit var tracker: Tracker

    companion object {
        private const val JOB_ID = 2000

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(
                context, TimerExpiredJobIntentService::class.java,
                JOB_ID, intent
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.injectTimerExpiredJobIntentService(this)
    }

    override fun onHandleWork(intent: Intent) {
        tracker.onTimerExpiredReceive()
    }
}
