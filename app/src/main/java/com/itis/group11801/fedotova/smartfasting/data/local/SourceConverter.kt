package com.itis.group11801.fedotova.smartfasting.data.local

import androidx.room.TypeConverter
import com.itis.group11801.fedotova.smartfasting.data.remote.model.Source

class SourceConverter {

    @TypeConverter
    fun fromSource(source: Source): String {
        var str = ""
        source.let {
            str = "${it.id} ${it.name}"
        }
        return str
    }

    @TypeConverter
    fun toSource(string: String): Source {
        if (string == "") return Source(null, null)
        val list = string.split(" ")
        return Source(list[0], list[1])
    }
}