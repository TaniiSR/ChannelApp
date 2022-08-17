package com.task.channelapp.ui.main.channeladapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.channelapp.databinding.LayoutItemEpisodeBinding
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.ui.main.channeladapter.episdoelistadapter.EpisodeListAdapter
import com.task.channelapp.utils.base.interfaces.OnItemClickListener


class EpisodeListViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val episodeAdapter: EpisodeListAdapter = EpisodeListAdapter(mutableListOf())

    @SuppressLint("SetTextI18n")
    fun onBind(
        data: ChannelData,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemEpisodeBinding -> {
                episodeAdapter.onItemClickListener = onItemClickListener
                episodeAdapter.updateEpisodeListItems(data.episodes)
                binding.rvEpisodeList.adapter = episodeAdapter
            }
        }
    }
}