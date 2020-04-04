package com.itis.group11801.fedotova.smartfasting.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.group11801.fedotova.smartfasting.R

class Navigator : NewsRouter {

    override fun intentOpenWebsite(fragment: Fragment, url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        fragment.startActivity(openURL)
    }

    override fun openFastsFragment(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.open_fasts_fragment)
    }
}
