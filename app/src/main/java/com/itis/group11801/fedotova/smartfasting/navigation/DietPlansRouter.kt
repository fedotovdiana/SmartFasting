package com.itis.group11801.fedotova.smartfasting.navigation

import android.os.Bundle

interface DietPlansRouter {

    fun openDietPlansFragment()

    fun openDietInfoFragment(bundle: Bundle)
}