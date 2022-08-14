package com.task.channelapp.data.local.localservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.channelapp.data.local.entities.CategoryEntity

@Dao
interface ChannelLocalDao {
    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<CategoryEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(currencies: List<CategoryEntity>)

}