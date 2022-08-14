package com.task.channelapp.data.remote.responsedtos

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<Category>? = null,
)