package com.task.channelapp.domain.interfaces

import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.ChannelDTO

interface IChannelDataRepo {
    suspend fun getAllChannels(isRefresh: Boolean): DataResponse<ChannelDTO>
}