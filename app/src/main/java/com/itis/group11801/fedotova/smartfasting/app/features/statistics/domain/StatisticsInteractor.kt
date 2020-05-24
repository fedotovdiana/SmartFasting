package com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain

import androidx.lifecycle.LiveData
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.data.local.model.DrinkSort
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerRepository
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import javax.inject.Inject

@AppScope
class StatisticsInteractor @Inject constructor(
    private val drinkRepository: DrinkRepository,
    private val trackerRepository: TrackerRepository
) {
    fun getDrinkNotes(): LiveData<List<DrinkNote>> {
        return drinkRepository.getDrinkNotes()
    }

    fun getDrinkVolumeTotal(): LiveData<Int> {
        return drinkRepository.getTotalVolume()
    }

    fun getDrinkVolumeAverage(): LiveData<DrinkSort?> {
        return drinkRepository.getAverageVolume()
    }

    fun isDrinkAdded(): Boolean {
        return drinkRepository.isDrinkAdded()
    }

    fun getTrackerNotes(): LiveData<List<TrackerNote>> {
        return trackerRepository.getTrackerNotes()
    }

    fun getTrackerNotesCount(): LiveData<Int> {
        return trackerRepository.getTrackerNotesCount()
    }

    fun getTrackerNotesMin(): LiveData<Long> {
        return trackerRepository.getTrackerNotesMin()
    }

    fun getTrackerNotesMax(): LiveData<Long> {
        return trackerRepository.getTrackerNotesMax()
    }

    fun getTrackerNotesAverage(): LiveData<Long> {
        return trackerRepository.getTrackerNotesAverage()
    }

    fun isTrackerNoteAdded(): Boolean {
        return trackerRepository.isTrackerNoteAdded()
    }
}
