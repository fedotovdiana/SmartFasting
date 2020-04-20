package com.itis.group11801.fedotova.smartfasting.feature_drink.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.utils.dateFormatToDate
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model.JournalChild
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model.JournalParent
import javax.inject.Inject

@AppScope
class DrinkInteractorImpl @Inject constructor(
    private val drinkRepository: DrinkRepository
) : DrinkInteractor {

    override suspend fun saveDrinkNote(drinkNote: DrinkNote) {
        drinkRepository.saveDrinkNote(drinkNote)
    }

    override fun getJournal(): LiveData<List<JournalParent>> {
        return Transformations.switchMap(drinkRepository.getDrinkNotes(), ::convertData)
    }

    private fun convertData(notes: List<DrinkNote>): LiveData<List<JournalParent>> {
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
