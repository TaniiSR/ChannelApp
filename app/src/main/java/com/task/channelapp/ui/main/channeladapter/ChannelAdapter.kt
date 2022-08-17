package com.task.channelapp.ui.main.channeladapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemChannelBinding
import com.task.channelapp.databinding.LayoutItemEpisodeBinding
import com.task.channelapp.databinding.LayoutItemSeriesBinding
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.utils.base.BaseRecyclerAdapter

class ChannelAdapter(
    private val list: MutableList<ChannelData>,
) : BaseRecyclerAdapter<ChannelData, EpisodeListViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): EpisodeListViewHolder {
        return EpisodeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeListViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return if (!list[position].episodes.isNullOrEmpty())
            R.layout.layout_item_episode
        else if (!list[position].series.isNullOrEmpty())
            R.layout.layout_item_series
        else
            R.layout.layout_item_channel
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            R.layout.layout_item_episode -> LayoutItemEpisodeBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
            R.layout.layout_item_series -> LayoutItemSeriesBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
            else -> LayoutItemChannelBinding.inflate(layoutInflater, viewGroup, false)
        }
    }

    fun updateChannelListItems(rateList: List<ChannelData>) {
        val diffCallback = ChannelDiffCallback(list, rateList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        this.setList(rateList)
        diffResult.dispatchUpdatesTo(this)
    }
}