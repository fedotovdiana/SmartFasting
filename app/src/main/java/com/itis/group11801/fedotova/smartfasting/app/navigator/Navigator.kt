package com.itis.group11801.fedotova.smartfasting.app.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.AppScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.drinks.DrinkRouter
import com.itis.group11801.fedotova.smartfasting.app.features.news.NewsRouter
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.StatisticsRouter
import javax.inject.Inject

@AppScope
class Navigator @Inject constructor(
    private val context: Context
) : NewsRouter, DietRouter, DrinkRouter, StatisticsRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController?, graph: Int) {
        navController?.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun intentOpenWebsite(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        context.startActivity(openURL)
    }

    override fun openDietPlansFragment() {
        navController?.navigate(R.id.open_diet_plans_fragment)
    }

    override fun openDietInfoFragment(bundle: Bundle) {
        navController?.navigate(R.id.open_diet_info, bundle)
    }

    override fun openTracker() {
        navController?.navigate(R.id.action_navigation_diet_info_to_navigation_tracker)
    }

    override fun openDrinkDialog() {
        navController?.navigate(R.id.navigation_choose_dialog)
    }

    override fun closeDrinkDialog() {
        navController?.navigate(R.id.navigation_drink_tracker)
    }

    override fun openConfirmStopDialogFragment() {
        navController?.navigate(R.id.navigation_confirm_stop_dialog)
    }

    override fun openDrinkJournal() {
        navController?.navigate(R.id.navigation_drink_journal)
    }
}
