package com.task.channelapp.data.remote.baseclient

import com.task.channelapp.data.remote.baseclient.erros.ApiError
import com.task.channelapp.data.remote.baseclient.models.BaseApiResponse


sealed class ApiResponse<out T : BaseApiResponse> {
    data class Success<out T : BaseApiResponse>(val code: Int, val data: T) : ApiResponse<T>()
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
}