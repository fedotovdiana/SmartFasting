package com.itis.group11801.fedotova.smartfasting.app.ui.utils

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

fun getRandomDrawableColor() = colors[Random.nextInt(colors.size)]

fun dateFormatFromString(oldStringDate: String): String {
    val newDate: String
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldStringDate
    }
    return newDate
}

fun dateFormat(oldDate: Date): String {
    val newDate: String
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
    newDate = try {
        dateFormat.format(oldDate)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldDate.toString()
    }
    return newDate
}

fun getCountry(): String {
    val locale: Locale = Locale.getDefault()
    val country: String = java.lang.String.valueOf(locale.country)
    return country.toLowerCase(Locale.ROOT)
}
