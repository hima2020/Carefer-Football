package org.carefer.football.ui.home.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ObjectConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): Any? {
        if (value == null) return null
        val type = object : TypeToken<Any>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toString(value: Any?): String? {
        if (value == null) return null
        return gson.toJson(value)
    }
}