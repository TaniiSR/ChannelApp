package com.task.channelapp.data.local.localservice

import com.task.channelapp.data.local.entities.CategoryEntity
import javax.inject.Inject

class ChannelLocalRepo @Inject constructor(private val channelLocalDao: ChannelLocalDao) :
    ChannelDbService {
    override suspend fun getCategories(): List<CategoryEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun insertCategories(categories: List<CategoryEntity>) {
        TODO("Not yet implemented")
    }
}