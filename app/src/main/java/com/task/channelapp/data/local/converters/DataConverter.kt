package com.task.channelapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity

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

    @TypeConverter
    fun fromMediaList(rates: List<MediaEntity>?): String? {
        if (rates == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<MediaEntity>?>() {}.type
        return gson.toJson(rates, type)
    }

    @TypeConverter
    fun toMediaList(rates: String?): List<MediaEntity>? {
        if (rates == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<MediaEntity>?>() {}.type
        return gson.fromJson(rates, type)
    }

    @TypeConverter
    fun fromChannelList(rates: List<ChannelEntity>?): String? {
        if (rates == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ChannelEntity>?>() {}.type
        return gson.toJson(rates, type)
    }

    @TypeConverter
    fun toChannelList(rates: String?): List<ChannelEntity>? {
        if (rates == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ChannelEntity>?>() {}.type
        return gson.fromJson(rates, type)
    }

    @TypeConverter
    fun channelToJson(value: ChannelEntity?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToChannel(string: String?): ChannelEntity? {
        return Gson().fromJson(string, ChannelEntity::class.java)
    }

}