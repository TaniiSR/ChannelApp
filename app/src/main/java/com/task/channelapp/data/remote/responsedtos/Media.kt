package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("channel")
    val channel: Channel? = null,
    @SerializedName("coverAsset")
    val coverAsset: ImageAsset? = null
)