package com.task.channelapp.data.local.localservice

import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity
import javax.inject.Inject

class ChannelLocalRepo @Inject constructor(private val channelLocalDao: ChannelLocalDao) :
    ChannelDbService {
    override suspend fun getCategories(): List<CategoryEntity>? {
        return channelLocalDao.getAllCategories()
    }

    override suspend fun insertCategories(categories: List<CategoryEntity>) {
        return channelLocalDao.insertAllCategories(categories)
    }

    override suspend fun getChannels(): List<ChannelEntity>? {
        return channelLocalDao.getAllChannels()
    }

    override suspend fun insertChannels(channels: List<ChannelEntity>) {
        channelLocalDao.insertAllChannels(channels)
    }

    override suspend fun getEpisodes(): List<MediaEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun insertEpisodes(episodes: List<MediaEntity>) {
        TODO("Not yet implemented")
    }
}