package com.task.channelapp.data.local.localservice

import com.task.channelapp.data.local.entities.CategoryEntity

interface ChannelDbService {
    suspend fun getCategories(): List<CategoryEntity>?
    suspend fun insertCategories(categories: List<CategoryEntity>)
}