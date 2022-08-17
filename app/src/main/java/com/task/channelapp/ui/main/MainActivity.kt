package com.task.channelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.task.channelapp.databinding.ActivityMainBinding
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.utils.base.BaseActivity
import com.task.channelapp.utils.base.interfaces.OnItemClickListener
import com.task.channelapp.utils.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, IMain>() {

    override val viewModel: IMain by viewModels<MainVM>()
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
        viewModel.getDataFromRepos(false)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        viewModel.channelAdapter.onItemClickListener = rvItemClickListener
        mViewBinding.rvChannels.adapter = viewModel.channelAdapter
    }

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is MediaData -> {
                    showToast(data.title)
                }
            }
        }
    }

    private fun handEpisodes(episodes: List<MediaData>) {
        viewModel.channelAdapter.updateChannelListItems(listOf(ChannelData(episodes = episodes)))
    }

    private fun viewModelObservers() {
        observe(viewModel.episodes, ::handEpisodes)
    }

}