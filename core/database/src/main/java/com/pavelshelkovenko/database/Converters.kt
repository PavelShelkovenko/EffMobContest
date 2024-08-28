package com.pavelshelkovenko.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return string.split(",").map { it }
    }
}