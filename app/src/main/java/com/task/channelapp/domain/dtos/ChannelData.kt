package com.task.channelapp.domain.dtos

data class ChannelData(
    val title: String = "",
    val id: String? = null,
    val slug: String? = null,
    val mediaCount: Int = -1,
    val coverAsset: String = "",
    val iconAsset: String = "",
    val series: List<MediaData> = emptyList(),
    val categories: List<CategoryData> = emptyList(),
    val episodes: List<MediaData> = emptyList(),
    val latestMedia: List<MediaData> = emptyList(),
)