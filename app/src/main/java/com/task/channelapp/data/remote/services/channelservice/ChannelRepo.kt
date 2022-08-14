package com.task.channelapp.data.remote.services.channelservice

import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.BaseRepository
import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.responsedtos.ChannelResponse
import com.task.channelapp.data.remote.responsedtos.EpisodeResponse
import javax.inject.Inject

class ChannelRepo @Inject constructor(
    private val service: ChannelRetroApi,
) : BaseRepository(),
    ChannelApi {

    override suspend fun fetchCategories(): ApiResponse<BaseResponse<CategoryResponse>> {
        return executeSafely(call = {
            service.fetchCategories()
        })
    }

    override suspend fun fetchEpisodes(): ApiResponse<BaseResponse<EpisodeResponse>> {
        return executeSafely(call = {
            service.fetchEpisodes()
        })
    }

    override suspend fun fetchChannels(): ApiResponse<BaseResponse<ChannelResponse>> {
        return executeSafely(call = {
            service.fetchChannels()
        })
    }

    companion object {
        const val URL_GET_EPISODES = "raw/z5AExTtw"
        const val URL_GET_CHANNELS = "raw/Xt12uVhM"
        const val URL_GET_CATEGORIES = "raw/A0CgArX3"
    }

}