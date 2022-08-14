package com.task.channelapp.data.remote.services.channelservice

import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.responsedtos.ChannelResponse
import com.task.channelapp.data.remote.responsedtos.EpisodeResponse

interface ChannelApi {
    suspend fun fetchCategories(): ApiResponse<BaseResponse<CategoryResponse>>
    suspend fun fetchEpisodes(): ApiResponse<BaseResponse<EpisodeResponse>>
    suspend fun fetchChannels(): ApiResponse<BaseResponse<ChannelResponse>>
}