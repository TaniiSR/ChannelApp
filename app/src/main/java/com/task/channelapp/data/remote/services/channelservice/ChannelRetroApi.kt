package com.task.channelapp.data.remote.services.channelservice

import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.responsedtos.ChannelResponse
import com.task.channelapp.data.remote.responsedtos.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChannelRetroApi {
    @GET(ChannelRepo.URL_GET_CATEGORIES)
    suspend fun fetchCategories(): Response<BaseResponse<CategoryResponse>>

    @GET(ChannelRepo.URL_GET_EPISODES)
    suspend fun fetchEpisodes(): Response<BaseResponse<EpisodeResponse>>

    @GET(ChannelRepo.URL_GET_CHANNELS)
    suspend fun fetchChannel(): Response<BaseResponse<ChannelResponse>>
}