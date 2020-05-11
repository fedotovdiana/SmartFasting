package com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.DrinkRepository
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TrackerRepository
import javax.inject.Inject

@AppScope
class StatisticsInteractor @Inject constructor(
    private val drinkRepository: DrinkRepository,
    private val trackerRepository: TrackerRepository
) {
    fun getDrinkNotes(): LiveData<List<DrinkNote>> {
        return drinkRepository.getDrinkNotes()
    }

    fun getTotalDrinkVolume(): LiveData<Int> {
        return drinkRepository.getTotalVolume()
    }

    fun getAverageDrinkVolume(): LiveData<Int> {
        return drinkRepository.getAverageVolume()
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
}
