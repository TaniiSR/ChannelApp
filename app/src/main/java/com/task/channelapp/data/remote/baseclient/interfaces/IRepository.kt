package com.task.channelapp.data.remote.baseclient.interfaces


import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.BaseApiResponse
import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}