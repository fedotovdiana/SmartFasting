package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.StatisticsRouter
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain.StatisticsInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.model.TrackerNote
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormat
import javax.inject.Inject

@ScreenScope
class StatisticsViewModel @Inject constructor(
    private val interactor: StatisticsInteractor,
    private val resourceManager: ResourceManager,
    private val router: StatisticsRouter
) : ViewModel() {

    val trackerNotes: LiveData<String> =
        interactor.getTrackerNotes().map { it.toString() }

    val data: LiveData<BarData> = interactor.getTrackerNotes().map(::mapToChartData)

    val drinkVolumeTotal: LiveData<String> =
        interactor.getDrinkVolumeTotal().map { it.toString() }

    val drinkVolumeAverage: LiveData<String> =
        interactor.getDrinkVolumeAverage().map { it?.name ?: "" }

    val trackerNotesCount: LiveData<String> =
        interactor.getTrackerNotesCount().map { it.toString() }

    val trackerNotesMin: LiveData<String> =
        interactor.getTrackerNotesMin().map { it.toString() }

    val trackerNotesMax: LiveData<String> =
        interactor.getTrackerNotesMax().map { it.toString() }

    val trackerNotesAverage: LiveData<String> =
        interactor.getTrackerNotesAverage().map { it.toString() }

    private var _labels = MutableLiveData<MutableList<String>>()
    val labels: LiveData<MutableList<String>>
        get() = _labels

    fun openDrinkJournal() {
        router.openDrinkJournal()
    }

    private fun mapToChartData(list: List<TrackerNote>): BarData {
        val entities = ArrayList<BarEntry>()
        val labelsList = ArrayList<String>()
        var i = 0
        while (i < list.size) {
            entities.add(BarEntry(i.toFloat(), list[i].time.toFloat()))
            labelsList.add(dateFormat(list[i].date))
            i++
        }
        _labels.value = labelsList

        val barDataSet = BarDataSet(entities, "")
        barDataSet.highLightAlpha = 40
        barDataSet.color = resourceManager.getColor(R.color.colorAccent)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.3f
        barData.setValueTextSize(10f)
        barData.setDrawValues(false)
        barData.setValueTextColor(resourceManager.getColor(R.color.colorTextDark))
        return barData
    }

    fun getTextColor() = resourceManager.getColor(R.color.colorTextDark)
}
