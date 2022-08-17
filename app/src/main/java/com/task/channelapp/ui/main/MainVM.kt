package com.task.channelapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.*
import com.task.channelapp.domain.interfaces.ICategoryDataRepo
import com.task.channelapp.domain.interfaces.IChannelDataRepo
import com.task.channelapp.domain.interfaces.IEpisodeDataRepo
import com.task.channelapp.ui.main.channeladapter.ChannelAdapter
import com.task.channelapp.utils.base.BaseViewModel
import com.task.channelapp.utils.base.sealed.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    private val _totalChannel: MutableLiveData<List<ChannelData>> = MutableLiveData()
    override val totalChannel: LiveData<List<ChannelData>> = _totalChannel

    private val _uiState: MutableLiveData<UIEvent> = MutableLiveData()
    override val uiState: LiveData<UIEvent> = _uiState

    override val channelAdapter: ChannelAdapter = ChannelAdapter(mutableListOf())

    override fun getDataFromRepos(isRefresh: Boolean) {
        _uiState.value = UIEvent.Loading
        fetchEpisodesChannelsAndCategories(isRefresh) { episodesResponse,
                                                        channelsResponse,
                                                        categoriesResponse ->
            handleEpisodes(episodesResponse)
            handleChannels(channelsResponse)
            handleCategories(categoriesResponse)
            if (_uiState.value == UIEvent.Loading) {
                val list: ArrayList<ChannelData> = arrayListOf()
                list.add(ChannelData(episodes = _episodes.value ?: emptyList()))
                list.addAll(channels.value ?: arrayListOf())
                _totalChannel.value = list
                _uiState.value = UIEvent.Success
            }
        }
    }

    private fun handleCategories(categoriesResponse: DataResponse<CategoryDTO>) {
        when (categoriesResponse) {
            is DataResponse.Success -> {
                _categories.value = categoriesResponse.data.categories
            }
            is DataResponse.Error -> {
                _uiState.value = UIEvent.Error(categoriesResponse.error.message)
                _categories.value = null
            }
        }
    }

    private fun handleEpisodes(episodesResponse: DataResponse<MediaDTO>) {
        when (episodesResponse) {
            is DataResponse.Success -> {
                _episodes.value = episodesResponse.data.media
            }
            is DataResponse.Error -> {
                _uiState.value = UIEvent.Error(episodesResponse.error.message)
                _episodes.value = null
            }
        }
    }

    private fun handleChannels(channelsResponse: DataResponse<ChannelDTO>) {
        when (channelsResponse) {
            is DataResponse.Success -> {
                _channels.value = channelsResponse.data.channels
            }
            is DataResponse.Error -> {
                _uiState.value = UIEvent.Error(channelsResponse.error.message)
                _channels.value = null
            }
        }
    }

    private fun fetchEpisodesChannelsAndCategories(
        isRefresh: Boolean,
        responses: (
            DataResponse<MediaDTO>,
            DataResponse<ChannelDTO>,
            DataResponse<CategoryDTO>
        ) -> Unit
    ) {
        launch {
            val episodesResponseDeferred = launchAsync {
                episodeRep.getAllNewEpisodes(isRefresh)
            }
            val channelsResponseDeferred = launchAsync {
                channelRepo.getAllChannels(isRefresh)
            }
            val categoryRepoResponseDeferred = launchAsync {
                categoryRepo.getAllCategories(isRefresh)
            }
            withContext(Dispatchers.Main) {
                responses(
                    episodesResponseDeferred.await(),
                    channelsResponseDeferred.await(),
                    categoryRepoResponseDeferred.await()
                )
            }
        }
    }
}