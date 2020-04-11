package com.itis.group11801.fedotova.smartfasting.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManagerImpl @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(resource: Int): String {
        return context.getString(resource)
    }

    override fun getColor(res: Int): Int {
        return ContextCompat.getColor(context, res)
    }

    override fun getDrawable(res: Int): Drawable? {
        return ContextCompat.getDrawable(context, res)
    }

    override fun getQuantityString(id: Int, quantity: Int): String {
        return context.resources.getQuantityString(id, quantity)
    }
}
