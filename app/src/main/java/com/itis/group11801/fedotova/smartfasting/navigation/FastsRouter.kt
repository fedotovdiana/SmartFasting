package com.itis.group11801.fedotova.smartfasting.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment

interface FastsRouter {

    fun openFastsFragment(fragment: Fragment)

    fun openFastInfoFragment(fragment: Fragment, bundle: Bundle)
}