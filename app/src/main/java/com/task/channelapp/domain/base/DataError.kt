package com.task.channelapp.domain.base

data class DataError(
    val code: Int,
    val message: String = "",
)