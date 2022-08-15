package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("mediaCount")
    val mediaCount: Int? = null,
    @SerializedName("coverAsset")
    val coverAsset: ImageAsset? = null,
    @SerializedName("iconAsset")
    val iconAsset: ImageAsset? = null,
    @SerializedName("series")
    val series: List<Media>? = null,
    @SerializedName("latestMedia")
    val latestMedia: List<Media>? = null,
)
