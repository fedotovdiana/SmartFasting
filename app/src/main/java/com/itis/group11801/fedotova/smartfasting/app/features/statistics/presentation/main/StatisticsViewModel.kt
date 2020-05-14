package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.StatisticsRouter
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain.StatisticsInteractor
import javax.inject.Inject

@ScreenScope
class StatisticsViewModel @Inject constructor(
    private val interactor: StatisticsInteractor,
    private val router: StatisticsRouter
) : ViewModel() {

    val drinkVolumeTotal: LiveData<String> =
        interactor.getDrinkVolumeTotal().map { it.toString() }

    val drinkVolumeAverage: LiveData<String> =
        interactor.getDrinkVolumeAverage().map { it.name }

    val trackerNotesCount: LiveData<String> =
        interactor.getTrackerNotesCount().map { it.toString() }

    val trackerNotesMin: LiveData<String> =
        interactor.getTrackerNotesMin().map { it.toString() }

    val trackerNotesMax: LiveData<String> =
        interactor.getTrackerNotesMax().map { it.toString() }

    val trackerNotesAverage: LiveData<String> =
        interactor.getTrackerNotesAverage().map { it?.toString() }

    fun openDrinkJournal() {
        router.openDrinkJournal()
    }
}
