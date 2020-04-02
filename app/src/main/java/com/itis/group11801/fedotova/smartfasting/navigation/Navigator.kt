package com.itis.group11801.fedotova.smartfasting.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

class Navigator : NewsRouter {

    override fun intentOpenWebsite(fragment: Fragment, url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        fragment.startActivity(openURL)
    }
}
