package com.itis.group11801.fedotova.smartfasting.domain.interactor

import com.itis.group11801.fedotova.smartfasting.data.local.model.DrinkNote
import com.itis.group11801.fedotova.smartfasting.data.repository.DrinkRepository
import javax.inject.Inject

class DrinkInteractorImpl @Inject constructor(
    private val drinkRepository: DrinkRepository
) : DrinkInteractor {

    override suspend fun saveDrinkNote(drinkNote: DrinkNote) {
        drinkRepository.saveDrinkNote(drinkNote)
    }
}
