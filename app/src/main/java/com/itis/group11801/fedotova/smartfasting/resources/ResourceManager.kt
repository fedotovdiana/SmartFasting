package com.itis.group11801.fedotova.smartfasting.resources

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes resource: Int): String

    fun getColor(@ColorRes res: Int): Int

    fun getDrawable(@DrawableRes res: Int): Drawable?

    fun getQuantityString(id: Int, quantity: Int): String
}
