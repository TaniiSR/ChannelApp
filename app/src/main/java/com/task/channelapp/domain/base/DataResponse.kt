package com.task.channelapp.domain.base

sealed class DataResponse<out T : BaseDataResponse> {
    data class Success<out T : BaseDataResponse>(val data: T) : DataResponse<T>()
    data class Error(val error: DataError) : DataResponse<Nothing>()
}