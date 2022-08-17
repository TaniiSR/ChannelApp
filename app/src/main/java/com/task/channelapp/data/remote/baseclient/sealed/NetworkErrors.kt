package com.task.channelapp.data.remote.baseclient.sealed

import com.task.channelapp.domain.dtos.sealed.MediaType

sealed class NetworkErrors {
    object NoInternet : NetworkErrors()
    object RequestTimedOut : NetworkErrors()
    object BadGateway : NetworkErrors()
    object NotFound : NetworkErrors()
    object Forbidden : NetworkErrors()
    object SessionExpire : NetworkErrors()
    object InternalServerError : NetworkErrors()
    open class UnknownError : NetworkErrors()
}
