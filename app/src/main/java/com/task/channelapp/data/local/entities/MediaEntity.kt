package com.task.channelapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "coverAsset")
    val coverAsset: String,
    @ColumnInfo(name = "channel")
    val channel: ChannelEntity,
)
