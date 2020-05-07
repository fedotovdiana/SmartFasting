package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain.StatisticsInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalChild
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalParent
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormatToDate
import javax.inject.Inject

@ScreenScope
class DrinkJournalViewModel @Inject constructor(
    private val interactor: StatisticsInteractor,
    private val resourceManager: ResourceManager
) : ViewModel() {

    val journal: LiveData<List<JournalParent>> =
        _drinkNotes.map(::mapToJournal)

    private val _drinkNotes: LiveData<List<DrinkNote>>
        get() = interactor.getDrinkNotes()

    val data: LiveData<BarData> = journal.map(::mapToBarChartData)

    private var _labels = MutableLiveData<MutableList<String>>()
    val labels: LiveData<MutableList<String>>
        get() = _labels

    private fun mapToBarChartData(journal: List<JournalParent>): BarData {
        val entities = ArrayList<BarEntry>()
        val labelsList = ArrayList<String>()
        var i = 0
        while (i < journal.size) {
            entities.add(BarEntry(i.toFloat(), journal[i].totalVolume.toFloat()))
            labelsList.add(journal[i].date.substringBeforeLast(" "))
            i++
        }

        val barDataSet = BarDataSet(entities, "")
        barDataSet.highLightAlpha = 40
        barDataSet.color = resourceManager.getColor(R.color.colorAccent)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.3f
        barData.setValueTextSize(10f)
        barData.setDrawValues(false)
        barData.setValueTextColor(resourceManager.getColor(R.color.colorTextDark))
        _labels.value = labelsList
        return barData
    }


    private fun mapToJournal(notes: List<DrinkNote>): List<JournalParent> {
        val parentItems: MutableList<JournalParent> = ArrayList()
        if (notes.isNotEmpty()) {
            var childItems: MutableList<JournalChild>
            var i = 0
            var totalVolume = 0
            var curDate = dateFormatToDate(notes[0].date)
            childItems = ArrayList()
            while (i < notes.size) {
                val date = dateFormatToDate(notes[i].date)
                if (date == curDate) {
                    childItems.add(JournalChild(notes[i].drinkSort.name, notes[i].volume))
                    totalVolume += notes[i].volume
                    i++
                } else {
                    val dummyParentDataItem = JournalParent(curDate, totalVolume, childItems)
                    parentItems.add(dummyParentDataItem)
                    totalVolume = 0
                    curDate = date
                    childItems = ArrayList()
                }
            }
            val dummyParentDataItem = JournalParent(curDate, totalVolume, childItems)
            parentItems.add(dummyParentDataItem)
        }
        return parentItems
    }
}
