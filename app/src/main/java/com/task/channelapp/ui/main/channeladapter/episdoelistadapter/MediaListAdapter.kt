package com.task.channelapp.ui.main.channeladapter.episdoelistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemChannelChildBinding
import com.task.channelapp.databinding.LayoutItemEpisodeChildBinding
import com.task.channelapp.databinding.LayoutItemSeriesChildBinding
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.domain.dtos.sealed.MediaType
import com.task.channelapp.ui.main.channeladapter.diffutils.MediaDiffCallback
import com.task.channelapp.utils.base.BaseRecyclerAdapter

class MediaListAdapter(
    private val list: MutableList<MediaData>,
) : BaseRecyclerAdapter<MediaData, MediaListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): MediaListItemViewHolder {
        return MediaListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].mediaType) {
            MediaType.Episode ->
                R.layout.layout_item_episode_child
            MediaType.Series ->
                R.layout.layout_item_series_child
            else ->
                R.layout.layout_item_channel_child
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            R.layout.layout_item_episode_child -> LayoutItemEpisodeChildBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
            R.layout.layout_item_series_child -> LayoutItemSeriesChildBinding.inflate(
                layoutInflater,
                viewGroup,
                false
            )
            else -> LayoutItemChannelChildBinding.inflate(layoutInflater, viewGroup, false)
        }
    }

    fun updateEpisodeListItems(rateList: List<MediaData>) {
        val diffCallback = MediaDiffCallback(list, rateList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        this.setList(rateList)
        diffResult.dispatchUpdatesTo(this)
    }
}