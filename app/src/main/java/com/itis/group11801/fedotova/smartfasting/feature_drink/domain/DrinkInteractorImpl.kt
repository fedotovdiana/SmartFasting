package com.itis.group11801.fedotova.smartfasting.feature_drink.domain

import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.feature_drink.presentation.model.DrinkNoteUI
import javax.inject.Inject

@AppScope
class DrinkInteractorImpl @Inject constructor(
    private val drinkRepository: DrinkRepository
) : DrinkInteractor {

    override suspend fun saveDrinkNote(drinkNote: DrinkNoteUI) {
        drinkRepository.saveDrinkNote(drinkNote)
    }
}
