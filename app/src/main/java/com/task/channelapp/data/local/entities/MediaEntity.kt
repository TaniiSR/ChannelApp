package com.task.channelapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "id")
    val id: String? = null,
    @ColumnInfo(name = "coverAsset")
    val coverAsset: String? = null,
    @ColumnInfo(name = "channel")
    val channel: ChannelEntity? = null,
)
