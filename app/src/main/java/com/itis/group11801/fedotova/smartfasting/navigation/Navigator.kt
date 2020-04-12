package com.itis.group11801.fedotova.smartfasting.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.scope.AppScope
import javax.inject.Inject

@AppScope
class Navigator @Inject constructor(
    private val context: Context
) : NewsRouter, DietPlansRouter, DrinkRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController?, graph: Int) {
        Toast.makeText(context, "ATTACHED", Toast.LENGTH_LONG).show()
        navController?.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        Toast.makeText(context, "DETTACCHED", Toast.LENGTH_LONG).show()
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
        if (navController == null) {
            Toast.makeText(
                context,
                "OPEN DIET PLANS",
                Toast.LENGTH_LONG
            ).show()
        }
        navController?.navigate(R.id.open_diet_plans_fragment)
    }

    override fun openDietInfoFragment(bundle: Bundle) {
        navController?.navigate(R.id.open_diet_info, bundle)
    }

    override fun openDrinkDialog() {
        navController?.navigate(R.id.navigation_choose_dialog)
    }

    override fun closeDrinkDialog() {
        navController?.navigate(R.id.navigation_drink_tracker)
    }
}
