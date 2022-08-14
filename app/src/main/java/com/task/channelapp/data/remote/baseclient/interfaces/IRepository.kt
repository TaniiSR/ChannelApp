package com.task.channelapp.data.remote.baseclient.interfaces


import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.models.BaseApiResponse
import com.task.channelapp.data.remote.baseclient.erros.ApiError
import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
    fun <T> detectError(response: Response<T>): ApiError
}