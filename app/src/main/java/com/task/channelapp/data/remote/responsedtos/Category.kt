package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    val name: String? = null
)
