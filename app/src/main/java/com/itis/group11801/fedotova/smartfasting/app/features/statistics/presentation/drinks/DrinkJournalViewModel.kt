package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.domain.StatisticsInteractorImpl
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalChild
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalParent
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormatToDate
import javax.inject.Inject

@ScreenScope
class DrinkJournalViewModel @Inject constructor(
    private val interactor: StatisticsInteractorImpl
) : ViewModel() {

    val journal: LiveData<List<JournalParent>> =
        Transformations.switchMap(interactor.getDrinkNotes(), ::convertToJournal)

    private fun convertToJournal(notes: List<DrinkNote>): LiveData<List<JournalParent>> {
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
        return MutableLiveData(parentItems)
    }
}
