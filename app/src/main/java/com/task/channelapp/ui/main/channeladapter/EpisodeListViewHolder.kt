package com.task.channelapp.ui.main.channeladapter

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemChannelBinding
import com.task.channelapp.databinding.LayoutItemEpisodeBinding
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.ui.main.channeladapter.episdoelistadapter.MediaListAdapter
import com.task.channelapp.utils.base.interfaces.OnItemClickListener
import com.task.channelapp.utils.extensions.loadImage


class EpisodeListViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val episodeAdapter: MediaListAdapter = MediaListAdapter(mutableListOf())
    private val mediaListAdapter: MediaListAdapter = MediaListAdapter(mutableListOf())

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
            is LayoutItemChannelBinding -> {
                binding.tvTitle.text = data.title
                binding.tvCount.text = binding.tvCount.context.getString(
                    R.string.screen_main_display_text_episodes,
                    data.latestMedia.size.toString()
                )
                binding.ivChannelIcon.loadImage(
                    data.iconAsset,
                    ContextCompat.getDrawable(
                        binding.ivChannelIcon.context,
                        R.drawable.ic_launcher_background
                    )
                )
                mediaListAdapter.onItemClickListener =
                    getItemClickItemListener(data, position, onItemClickListener)
                mediaListAdapter.updateEpisodeListItems(data.latestMedia)
                binding.rvChannelList.adapter = mediaListAdapter
            }
        }
    }

    private fun getItemClickItemListener(
        dataChannel: ChannelData,
        positionChannel: Int,
        onItemClickListener: OnItemClickListener?
    ): OnItemClickListener {
        val rvItemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View, data: Any, pos: Int) {
                onItemClickListener?.onItemClick(view, dataChannel, positionChannel)
            }
        }
        return rvItemClickListener
    }
}