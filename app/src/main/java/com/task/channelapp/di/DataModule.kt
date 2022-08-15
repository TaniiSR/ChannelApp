package com.task.channelapp.di


import com.task.channelapp.data.local.localservice.ChannelDbService
import com.task.channelapp.data.local.localservice.ChannelLocalRepo
import com.task.channelapp.data.remote.services.channelservice.ChannelApi
import com.task.channelapp.data.remote.services.channelservice.ChannelRepo
import com.task.channelapp.domain.DataRepository
import com.task.channelapp.domain.interfaces.ICategoryDataRepo
import com.task.channelapp.domain.interfaces.IChannelDataRepo
import com.task.channelapp.domain.interfaces.IEpisodeDataRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideChannelRemoteRepository(channelRepositoryRemote: ChannelRepo): ChannelApi

    @Binds
    @Singleton
    abstract fun provideChannelLocalRepository(channelRepoLocal: ChannelLocalRepo): ChannelDbService

    @Binds
    @Singleton
    abstract fun provideChannelRepository(dataRepository: DataRepository): IChannelDataRepo

    @Binds
    @Singleton
    abstract fun provideCategoryRepository(dataRepository: DataRepository): ICategoryDataRepo

    @Binds
    @Singleton
    abstract fun provideEpisodeDataRepoRepository(dataRepository: DataRepository): IEpisodeDataRepo
}