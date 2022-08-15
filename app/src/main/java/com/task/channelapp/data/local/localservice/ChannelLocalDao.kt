package com.task.channelapp.data.local.localservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity

@Dao
interface ChannelLocalDao {
    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<CategoryEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM channel")
    suspend fun getAllChannels(): List<ChannelEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllChannels(channels: List<ChannelEntity>)

    @Query("SELECT * FROM media")
    suspend fun getAllEpisodes(): List<MediaEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<MediaEntity>)


}