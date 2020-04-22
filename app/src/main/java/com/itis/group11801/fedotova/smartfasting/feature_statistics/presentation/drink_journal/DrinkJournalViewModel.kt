package com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormatToDate
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkInteractorImpl
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model.JournalChild
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model.JournalParent
import javax.inject.Inject

@ScreenScope
class DrinkJournalViewModel @Inject constructor(
    private val interactor: DrinkInteractorImpl
) : ViewModel() {

    val journal: LiveData<List<JournalParent>> =
        Transformations.switchMap(interactor.getJournal(), ::convertToJournal)

    private fun convertToJournal(notes: List<DrinkNote>): LiveData<List<JournalParent>> {
        val parentItems: MutableList<JournalParent> = ArrayList()
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
        return MutableLiveData(parentItems)
    }
}
