package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class ImageAsset(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null
)