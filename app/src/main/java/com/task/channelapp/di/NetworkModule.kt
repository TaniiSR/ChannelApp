package com.task.channelapp.di


import com.task.channelapp.data.remote.baseclient.RetroNetwork
import com.task.channelapp.data.remote.services.channelservice.ChannelRetroApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesGitRepoRetroService(): ChannelRetroApi =
        RetroNetwork().createService(ChannelRetroApi::class.java)


}