package com.task.channelapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.task.channelapp.R
import com.task.channelapp.databinding.ActivityMainBinding
import com.task.channelapp.domain.dtos.CategoryData
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.utils.base.BaseActivity
import com.task.channelapp.utils.base.interfaces.OnItemClickListener
import com.task.channelapp.utils.base.sealed.UIEvent
import com.task.channelapp.utils.extensions.gone
import com.task.channelapp.utils.extensions.observe
import com.task.channelapp.utils.extensions.visible
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
        mViewBinding.errorView.btnRetry.setOnClickListener(this)
        mViewBinding.swRefresh.setOnRefreshListener {
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.getDataFromRepos(true)
        }
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnRetry -> viewModel.getDataFromRepos(true)
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

    private fun handleUIState(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.Loading -> setLoadingView()
            is UIEvent.Success -> setSuccessView()
            is UIEvent.Error -> setErrorView()
            is UIEvent.Message -> showToast(uiEvent.message)
        }
    }

    private fun setLoadingView() {
        mViewBinding.errorView.errorView.gone()
        mViewBinding.rvChannels.gone()
        showShimmerLoading()
    }

    private fun setSuccessView() {
        mViewBinding.rvChannels.visible()
        mViewBinding.errorView.errorView.gone()
        hideShimmerLoading()
    }

    private fun setErrorView() {
        mViewBinding.errorView.errorView.visible()
        mViewBinding.rvChannels.gone()
        hideShimmerLoading()
    }

    private fun showShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.visible()
        mViewBinding.shimmerLayout.shimmerFrameLayout.startShimmer()

    }

    private fun hideShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.gone()
        mViewBinding.shimmerLayout.shimmerFrameLayout.stopShimmer()
    }


    private fun viewModelObservers() {
        observe(viewModel.totalChannel, ::handleChannels)
        observe(viewModel.uiState, ::handleUIState)
    }


}