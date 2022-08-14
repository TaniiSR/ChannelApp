package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class ChannelResponse(
    @SerializedName("channels")
    val channels: List<Channel>? = null,
)
