package com.task.channelapp.domain.dtos

data class MediaData(
    val id: String?,
    val type: String,
    val title: String,
    val channel: ChannelData = ChannelData(""),
    val coverAsset: String
)