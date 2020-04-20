package com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.domain.DrinkInteractor
import com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal.model.JournalParent
import javax.inject.Inject

@ScreenScope
class DrinkJournalViewModel @Inject constructor(
    private val interactor: DrinkInteractor
) : ViewModel() {

    val journal: LiveData<List<JournalParent>> = interactor.getJournal()
}
