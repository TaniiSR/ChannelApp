package com.task.channelapp.data.remote.baseclient.sealed

import com.task.channelapp.domain.dtos.sealed.MediaType

sealed class NetworkErrors {
    object NoInternet : MediaType()
    object RequestTimedOut : MediaType()
    object BadGateway : MediaType()
    object NotFound : MediaType()
    object Forbidden : MediaType()
    object SessionExpire : MediaType()
    object InternalServerError : MediaType()
    open class UnknownError : MediaType()
}
