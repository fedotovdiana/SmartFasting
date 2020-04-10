package com.itis.group11801.fedotova.smartfasting.navigation

import android.os.Bundle

interface FastsRouter {

    fun openFastsFragment()

    fun openFastInfoFragment(bundle: Bundle)
}