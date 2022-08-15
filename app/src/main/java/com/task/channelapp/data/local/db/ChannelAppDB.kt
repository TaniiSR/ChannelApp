package com.task.channelapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.channelapp.data.local.converters.DataConverter
import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity
import com.task.channelapp.data.local.localservice.ChannelLocalDao

@Database(
    entities = [CategoryEntity::class, ChannelEntity::class, MediaEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class ChannelAppDB : RoomDatabase() {
    abstract fun channelLocalDao(): ChannelLocalDao
}