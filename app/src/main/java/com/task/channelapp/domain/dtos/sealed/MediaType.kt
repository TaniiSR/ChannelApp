package com.task.channelapp.domain.dtos.sealed

sealed class MediaType {
    object Channel : MediaType()
    object Series : MediaType()
    object Episode : MediaType()
}
