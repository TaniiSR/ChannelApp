package com.task.channelapp.domain.dtos

import com.task.channelapp.domain.base.BaseDataResponse

class ChannelDTO(
    val channels: List<ChannelData>
) : BaseDataResponse()