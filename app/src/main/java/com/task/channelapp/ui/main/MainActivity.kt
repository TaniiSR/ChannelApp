package com.task.channelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.task.channelapp.databinding.ActivityMainBinding
import com.task.channelapp.domain.dtos.CategoryData
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
        setListener()
        viewModel.getDataFromRepos(false)
        setUpRecyclerView()
    }

    private fun setListener() {
        mViewBinding.swRefresh.setOnRefreshListener {
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.getDataFromRepos(true)
        }
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
                is ChannelData ->
                    showToast(data.title)
                is CategoryData ->
                    showToast(data.name)
            }
        }
    }

    private fun handleChannels(channels: List<ChannelData>) {
        viewModel.channelAdapter.updateChannelListItems(channels)
    }

    private fun viewModelObservers() {
        observe(viewModel.totalChannel, ::handleChannels)
    }


}