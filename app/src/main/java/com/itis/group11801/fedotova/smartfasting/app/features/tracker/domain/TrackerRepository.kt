package com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote

interface TrackerRepository {

    suspend fun saveTrackerNote(trackerNote: TrackerNote)

    fun getTrackerNotes(): LiveData<List<TrackerNote>>

    fun getTrackerNotesCount(): LiveData<Int>

    fun getTrackerNotesMin(): LiveData<Long>

    fun getTrackerNotesMax(): LiveData<Long>

    fun getTrackerNotesAverage(): LiveData<Long>

    fun getTimerLength(): Long

    fun getRemainingSeconds(): Long

    fun setRemainingSeconds(time: Long)

    fun resetRemainingSeconds()

    fun getAlarmSetTime(): Long

    fun setAlarmSetTime(time: Long)

    fun isFirstLaunch(): Boolean
}
