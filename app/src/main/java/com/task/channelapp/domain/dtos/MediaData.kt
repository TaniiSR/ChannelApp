package com.task.channelapp.domain.dtos

import com.task.channelapp.domain.dtos.sealed.MediaType

data class MediaData(
    val id: String?,
    val type: String,
    val title: String,
    val mediaType: MediaType,
    val channel: ChannelData = ChannelData(""),
    val coverAsset: String
)