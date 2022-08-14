package com.task.channelapp.data.remote.baseclient.erros

data class ServerError(
    val code: Int?,
    val message: String?,
    val actualCode: String = "-1"
)
