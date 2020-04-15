package com.itis.group11801.fedotova.smartfasting.feature_diets

import android.os.Bundle

interface DietRouter {

    fun openDietPlansFragment()

    fun openDietInfoFragment(bundle: Bundle)
}