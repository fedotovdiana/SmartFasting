package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TimerState
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerInteractor
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ScreenScope
class TrackerViewModel @Inject constructor(
    private val interactor: TrackerInteractor,
    private val resourceManager: ResourceManager,
    private val router: DietRouter
) : ViewModel() {

    val progress: LiveData<Int>
        get() = interactor.getProgress().map { it.toInt() }

    val progressMax: LiveData<Int>
        get() = interactor.getProgressMax().map { it.toInt() }

    val progressText: LiveData<String>
        get() = interactor.getProgressTime().map { mapProgress(it) }

    val timerState: LiveData<TimerState>
        get() = interactor.getState()

    val startTime: LiveData<String>
        get() = interactor.getStartTime().map { mapStartTime(it) }

    private val _isFirstLaunch = MutableLiveData(true)
    val isFirstLaunch: LiveData<Boolean>
        get() = _isFirstLaunch

    init {
        _isFirstLaunch.value = interactor.isFirstLaunch()
    }

    fun startTimer() {
        interactor.startTimer()
    }

    fun stopTimer() {
        interactor.stopTimer()
    }

    fun resumeTimer() {
        interactor.resumeTimer()
    }

    fun openDiets() {
        router.openDietPlansFragment()
    }

    fun pauseTimer() {
        interactor.pauseTimer()
    }

    fun getTimerLength(): String {
        return "${(interactor.getTimerLength() / 3600)} h"
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

    private fun mapProgress(remainingSeconds: Long): String {
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
