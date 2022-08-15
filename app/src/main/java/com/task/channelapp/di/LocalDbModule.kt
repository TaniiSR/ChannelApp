package com.task.channelapp.di

import android.content.Context
import androidx.room.Room
import com.task.channelapp.data.local.db.ChannelAppDB
import com.task.channelapp.data.local.localservice.ChannelLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {
    @Provides
    fun provideExchangeDao(appDatabase: ChannelAppDB): ChannelLocalDao {
        return appDatabase.channelLocalDao()
    }

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): ChannelAppDB {
        return Room.databaseBuilder(
            appContext,
            ChannelAppDB::class.java,
            "ChannelAppDB"
        ).build()
    }

}