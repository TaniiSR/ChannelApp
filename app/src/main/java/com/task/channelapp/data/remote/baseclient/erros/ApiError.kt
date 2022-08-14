package com.task.channelapp.data.remote.baseclient.erros

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("code")
    val statusCode: Int,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("actualCode")
    val actualCode: String = "-1"
)