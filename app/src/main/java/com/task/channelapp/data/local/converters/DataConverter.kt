package com.task.channelapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.channelapp.data.local.entities.CategoryEntity

class DataConverter {
    @TypeConverter
    fun fromCategoryList(rates: List<CategoryEntity>?): String? {
        if (rates == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<CategoryEntity>?>() {}.type
        return gson.toJson(rates, type)
    }

    @TypeConverter
    fun toCategoryList(rates: String?): List<CategoryEntity>? {
        if (rates == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<CategoryEntity>?>() {}.type
        return gson.fromJson(rates, type)
    }

}