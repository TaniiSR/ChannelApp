package com.task.channelapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channel")
data class ChannelEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "mediaCount")
    val mediaCount: Int,
    @ColumnInfo(name = "coverAsset")
    val coverAsset: String,
    @ColumnInfo(name = "iconAsset")
    val iconAsset: String,
    @ColumnInfo(name = "series")
    val series: List<MediaEntity>,
    @ColumnInfo(name = "latestMedia")
    val latestMedia: List<MediaEntity>,
)