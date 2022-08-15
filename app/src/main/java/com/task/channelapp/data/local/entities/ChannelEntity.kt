package com.task.channelapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channel")
data class ChannelEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "id")
    val id: String? = null,
    @ColumnInfo(name = "slug")
    val slug: String? = null,
    @ColumnInfo(name = "mediaCount")
    val mediaCount: Int? = null,
    @ColumnInfo(name = "coverAsset")
    val coverAsset: String? = null,
    @ColumnInfo(name = "iconAsset")
    val iconAsset: String? = null,
    @ColumnInfo(name = "series")
    val series: List<MediaEntity>? = null,
    @ColumnInfo(name = "latestMedia")
    val latestMedia: List<MediaEntity>? = null,
)