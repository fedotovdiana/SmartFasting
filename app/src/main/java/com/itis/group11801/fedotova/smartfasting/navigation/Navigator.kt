package com.itis.group11801.fedotova.smartfasting.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.group11801.fedotova.smartfasting.R
import javax.inject.Singleton

@Singleton
class Navigator : NewsRouter, FastsRouter, DrinkRouter {

    override fun intentOpenWebsite(fragment: Fragment, url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        fragment.startActivity(openURL)
    }

    override fun openFastsFragment(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.open_fasts_fragment)
    }

    override fun openFastInfoFragment(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.open_fast_info, bundle)
    }

    override fun openDrinkDialog(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.navigation_choose_dialog)
    }

    override fun closeDrinkDialog(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.navigation_drink_tracker)
    }
}
