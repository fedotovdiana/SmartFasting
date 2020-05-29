package com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.TrackerDao
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.mapper.mapTrackerNoteLocalToTrackerNote
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.data.local.mapper.mapTrackerNoteToTrackerNoteLocal
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerRepository
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import com.itis.group11801.fedotova.smartfasting.app.helpers.managers.PreferenceManager
import javax.inject.Inject

@AppScope
class TrackerRepositoryImpl @Inject constructor(
    private val trackerDao: TrackerDao,
    private val preferenceManager: PreferenceManager
) : TrackerRepository {

    override suspend fun saveTrackerNote(trackerNote: TrackerNote) {
        trackerDao.insert(mapTrackerNoteToTrackerNoteLocal(trackerNote))
        preferenceManager.setIsTrackerNoteAdded()
    }

    override fun getTrackerNotes(): LiveData<List<TrackerNote>> {
        return trackerDao.getAll().map { list ->
            list.map { mapTrackerNoteLocalToTrackerNote(it) }
        }
    }

    override fun getTrackerNotesCount(): LiveData<Int> {
        return trackerDao.getCount()
    }

    override fun getTrackerNotesMin(): LiveData<Long> {
        return trackerDao.getMin()
    }

    override fun getTrackerNotesMax(): LiveData<Long> {
        return trackerDao.getMax()
    }

    override fun getTrackerNotesAverage(): LiveData<Long> {
        return trackerDao.getAverage()
    }

    override fun getTimerLength(): Long {
        return preferenceManager.getTimerLength()
    }

    override fun getRemainingSeconds(): Long {
        return preferenceManager.getRemainingSeconds()
    }

    override fun setRemainingSeconds(time: Long) {
        preferenceManager.setRemainingSeconds(time)
    }

    override fun resetRemainingSeconds() {
        preferenceManager.resetRemainingSeconds()
    }

    override fun getAlarmSetTime(): Long {
        return preferenceManager.getAlarmSetTime()
    }

    override fun setAlarmSetTime(time: Long) {
        preferenceManager.setAlarmSetTime(time)
    }

    override fun isDietAdded(): Boolean {
        return preferenceManager.isDietAdded()
    }

    override fun isTrackerNoteAdded(): Boolean {
        return preferenceManager.isTrackerNoteAdded()
    }
}
