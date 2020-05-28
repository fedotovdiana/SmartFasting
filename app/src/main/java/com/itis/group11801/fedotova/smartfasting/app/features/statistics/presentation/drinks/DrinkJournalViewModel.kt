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
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalChildUI
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalParentUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormat
import javax.inject.Inject

@ScreenScope
class DrinkJournalViewModel @Inject constructor(
    private val interactor: StatisticsInteractor,
    private val resourceManager: ResourceManager
) : ViewModel() {

    val journal: LiveData<List<JournalParentUI>> =
        _drinkNotes.map(::mapToJournal)

    val data: LiveData<BarData> = journal.map(::mapToBarChartData)

    private val _drinkNotes: LiveData<List<DrinkNote>>
        get() = interactor.getDrinkNotes()

    private var _labels = MutableLiveData<MutableList<String>>()
    val labels: LiveData<MutableList<String>>
        get() = _labels

    private fun mapToBarChartData(journal: List<JournalParentUI>): BarData {
        val entities = ArrayList<BarEntry>()
        val labelsList = ArrayList<String>()
        var i = 0
        while (i < journal.size) {
            entities.add(BarEntry(i.toFloat(), journal[i].totalVolume.toFloat()))
            labelsList.add(journal[i].date.substringBeforeLast(" "))
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

    private fun mapToJournal(notes: List<DrinkNote>): List<JournalParentUI> {
        val parentUIItems: MutableList<JournalParentUI> = ArrayList()
        if (notes.isNotEmpty()) {
            var childUIItems: MutableList<JournalChildUI>
            var i = 0
            var totalVolume = 0
            var curDate = dateFormat(notes[0].date)
            childUIItems = ArrayList()
            while (i < notes.size) {
                val date = dateFormat(notes[i].date)
                if (date == curDate) {
                    childUIItems.add(JournalChildUI(notes[i].drinkSort.name, notes[i].volume))
                    totalVolume += notes[i].volume
                    i++
                } else {
                    val dummyParentDataItem = JournalParentUI(curDate, totalVolume, childUIItems)
                    parentUIItems.add(dummyParentDataItem)
                    totalVolume = 0
                    curDate = date
                    childUIItems = ArrayList()
                }
            }
            val dummyParentDataItem = JournalParentUI(curDate, totalVolume, childUIItems)
            parentUIItems.add(dummyParentDataItem)
        }
        return parentUIItems
    }

    fun getTextColor() = resourceManager.getColor(R.color.colorTextDark)
}
