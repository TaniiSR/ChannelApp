package com.task.channelapp.domain

import com.task.channelapp.data.local.localservice.ChannelDbService
import com.task.channelapp.data.remote.services.channelservice.ChannelApi
import com.task.channelapp.domain.interfaces.ICategoryDataRepo
import com.task.channelapp.domain.interfaces.IChannelDataRepo
import com.task.channelapp.domain.interfaces.IEpisodeDataRepo
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteRepository: ChannelApi,
    private val localRepository: ChannelDbService
) : ICategoryDataRepo, IChannelDataRepo, IEpisodeDataRepo {
}