package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker.Tracker
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker.TrackerState
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ScreenScope
class TrackerViewModel @Inject constructor(
    private val tracker: Tracker,
    private val resourceManager: ResourceManager,
    private val router: DietRouter
) : ViewModel() {

    val progress: LiveData<Int>
        get() = tracker.progress.map { it.toInt() }

    val progressMax: LiveData<Int>
        get() = tracker.progressMax.map { it.toInt() }

    val progressText: LiveData<String>
        get() = tracker.progressRemaining.map { mapRemainTime(it) }

    val trackerState: LiveData<TrackerState>
        get() = tracker.state

    val endTime: LiveData<String>
        get() = tracker.endTime.map { mapStartTime(it) }

    fun checkDietAdded() = tracker.isDietAdded()

    fun startTimer() {
        tracker.startTimer()
    }

    fun stopTimer() {
        tracker.stopTimer()
    }

    fun resumeTimer() {
        tracker.resumeTimer()
    }

    fun pauseTimer() {
        tracker.saveTimer()
    }

    fun getTimerLength(): String {
        return "${(tracker.getTimerLength() / 3600)} h"
    }

    fun getStartText(): String {
        return resourceManager.getString(R.string.timer_on_start)
    }

    fun getStopText(): String {
        return resourceManager.getString(R.string.timer_on_stop)
    }

    fun openDialog() {
        router.openConfirmStopDialogFragment()
    }

    fun openDiets() {
        router.openDietPlansFragment()
    }

    private fun mapRemainTime(remainingSeconds: Long): String {
        val hours = remainingSeconds / 3600
        val minutes = remainingSeconds / 60 - hours * 60
        val seconds = remainingSeconds - hours * 3600 - minutes * 60
        return "${if (hours > 9) hours else "0$hours"}:" +
                "${if (minutes > 9) minutes else "0$minutes"}:" +
                "${if (seconds > 9) seconds else "0$seconds"}"
    }

    private fun mapStartTime(time: Long): String {
        val df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)
        return "End at ${df.format(Date(time))}"
    }
}
