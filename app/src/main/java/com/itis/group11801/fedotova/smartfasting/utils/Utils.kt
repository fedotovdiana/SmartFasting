package com.itis.group11801.fedotova.smartfasting.utils

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.itis.group11801.fedotova.smartfasting.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

var colors = arrayOf(
    R.color.colorGradientBlueFirst,
    R.color.colorGradientBlueSecond,
    R.color.colorGradientYellowFirst,
    R.color.colorGradientYellowSecond,
    R.color.colorGradientGreenFirst,
    R.color.colorGradientGreenSecond,
    R.color.colorGradientPurpleFirst,
    R.color.colorGradientPurpleSecond
)

fun getRandomDrawableColor() = colors[Random.nextInt(
    colors.size
)]

fun dateFormat(oldStringDate: String): String {
    val newDate: String
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale(getCountry()))
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldStringDate
    }
    return newDate
}

fun getCountry(): String {
    val locale: Locale = Locale.getDefault()
    val country: String = java.lang.String.valueOf(locale.country)
    return country.toLowerCase(Locale.ROOT)
}

fun intentOpenWebsite(fragment: Fragment, url: String) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(url)
    fragment.startActivity(openURL)
}
