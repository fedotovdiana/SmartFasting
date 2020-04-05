package com.itis.group11801.fedotova.smartfasting.navigation

import androidx.fragment.app.Fragment

interface DrinkRouter {

    fun openDrinkDialog(fragment: Fragment)

    fun closeDrinkDialog(fragment: Fragment)
}