package com.task.channelapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.channelapp.domain.dtos.CategoryData
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.domain.interfaces.ICategoryDataRepo
import com.task.channelapp.domain.interfaces.IChannelDataRepo
import com.task.channelapp.domain.interfaces.IEpisodeDataRepo
import com.task.channelapp.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val episodeRep: IEpisodeDataRepo,
    private val channelRepo: IChannelDataRepo,
    private val categoryRepo: ICategoryDataRepo
) : BaseViewModel(), IMain {

    private val _episodes: MutableLiveData<List<MediaData>> = MutableLiveData()
    override val episodes: LiveData<List<MediaData>> = _episodes

    private val _channels: MutableLiveData<List<ChannelData>> = MutableLiveData()
    override val channels: LiveData<List<ChannelData>> = _channels

    private val _categories: MutableLiveData<List<CategoryData>> = MutableLiveData()
    override val categories: LiveData<List<CategoryData>> = _categories

    override fun getDataFromRepos(isRefresh: Boolean) {
        TODO("Not yet implemented")
    }

}