package com.itis.group11801.fedotova.smartfasting.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.itis.group11801.fedotova.smartfasting.R

class Navigator : NewsRouter, FastsRouter, DrinkRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

//TODO openWebsite

//    override fun intentOpenWebsite(fragment: Fragment, url: String) {
//        val openURL = Intent(Intent.ACTION_VIEW)
//        openURL.data = Uri.parse(url)
//        fragment.startActivity(openURL)
//    }

    override fun openFastsFragment() {
        navController?.navigate(R.id.open_fasts_fragment)
    }

    override fun openFastInfoFragment(bundle: Bundle) {
        navController?.navigate(R.id.open_fast_info, bundle)
    }

    override fun openDrinkDialog() {
        navController?.navigate(R.id.navigation_choose_dialog)
    }

    override fun closeDrinkDialog() {
        navController?.navigate(R.id.navigation_drink_tracker)
    }
}
