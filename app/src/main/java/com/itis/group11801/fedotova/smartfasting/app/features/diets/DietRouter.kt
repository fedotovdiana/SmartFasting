package com.itis.group11801.fedotova.smartfasting.app.features.diets

import android.os.Bundle

interface DietRouter {

    fun openDietPlansFragment()

    fun openDietInfoFragment(bundle: Bundle)
}