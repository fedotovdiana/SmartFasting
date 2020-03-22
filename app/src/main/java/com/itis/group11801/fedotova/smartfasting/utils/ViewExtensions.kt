package com.itis.group11801.fedotova.smartfasting.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}
