package com.task.channelapp.data.remote.baseclient.models

import com.google.gson.annotations.SerializedName

class BaseResponse<T : Any> : BaseApiResponse() {
    @SerializedName("data")
    var data: T? = null
}