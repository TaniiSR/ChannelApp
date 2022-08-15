package com.task.channelapp.domain.dtos

import com.task.channelapp.domain.base.BaseDataResponse

class MediaDTO(
    val media: List<MediaData>
) : BaseDataResponse()