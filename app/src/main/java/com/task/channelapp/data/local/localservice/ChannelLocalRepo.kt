package com.task.channelapp.data.local.localservice

import javax.inject.Inject

class ChannelLocalRepo @Inject constructor(private val channelLocalDao: ChannelLocalDao) :
    ChannelDbService {
}