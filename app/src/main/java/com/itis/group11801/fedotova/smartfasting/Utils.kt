package com.itis.group11801.fedotova.smartfasting

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


var colors = arrayOf(

    ColorDrawable(Color.parseColor("#ffeead")),
    ColorDrawable(Color.parseColor("#93cfb3")),
    ColorDrawable(Color.parseColor("#fd7a7a")),
    ColorDrawable(Color.parseColor("#faca5f")),
    ColorDrawable(Color.parseColor("#1ba798")),
    ColorDrawable(Color.parseColor("#6aa9ae")),
    ColorDrawable(Color.parseColor("#ffbf27")),
    ColorDrawable(Color.parseColor("#d93947"))
)

fun getRandomDrawableColor() = colors[Random.nextInt(colors.size)]

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
