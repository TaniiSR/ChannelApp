package com.task.channelapp.domain.interfaces

import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.MediaDTO

interface IEpisodeDataRepo {
    suspend fun getAllNewEpisodes(isRefresh: Boolean): DataResponse<MediaDTO>
}