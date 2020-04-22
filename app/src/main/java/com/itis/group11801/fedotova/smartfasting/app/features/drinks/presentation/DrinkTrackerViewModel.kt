package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import javax.inject.Inject

@ScreenScope
class DrinkTrackerViewModel @Inject constructor(
    private val router: DrinkRouter
) : ViewModel() {

    fun openDrinkDialog() {
        router.openDrinkDialog()
    }
}
