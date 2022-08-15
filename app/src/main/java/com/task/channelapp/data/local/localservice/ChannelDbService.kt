package com.task.channelapp.data.local.localservice

import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity

interface ChannelDbService {
    suspend fun getCategories(): List<CategoryEntity>?
    suspend fun insertCategories(categories: List<CategoryEntity>)
    suspend fun getChannels(): List<ChannelEntity>?
    suspend fun insertChannels(channels: List<ChannelEntity>)
    suspend fun getEpisodes(): List<MediaEntity>?
    suspend fun insertEpisodes(episodes: List<MediaEntity>)
}