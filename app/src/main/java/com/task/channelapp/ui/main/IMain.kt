package com.task.channelapp.ui.main

import androidx.lifecycle.LiveData
import com.task.channelapp.domain.dtos.CategoryData
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.ui.main.channeladapter.ChannelAdapter
import com.task.channelapp.utils.base.interfaces.IBaseViewModel
import com.task.channelapp.utils.base.sealed.UIEvent

interface IMain : IBaseViewModel {
    val episodes: LiveData<List<MediaData>>
    val channels: LiveData<List<ChannelData>>
    val categories: LiveData<List<CategoryData>>
    val totalChannel: LiveData<List<ChannelData>>
    val uiState: LiveData<UIEvent>
    val channelAdapter: ChannelAdapter
    fun getDataFromRepos(isRefresh: Boolean)
}