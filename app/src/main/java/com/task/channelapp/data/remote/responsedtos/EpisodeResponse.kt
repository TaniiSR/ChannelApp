package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("media")
    val media: List<Media>? = null,
)