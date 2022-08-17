package com.task.channelapp.ui.main.channeladapter

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemCategoriesBinding
import com.task.channelapp.databinding.LayoutItemChannelBinding
import com.task.channelapp.databinding.LayoutItemEpisodeBinding
import com.task.channelapp.databinding.LayoutItemSeriesBinding
import com.task.channelapp.domain.dtos.ChannelData
import com.task.channelapp.ui.main.channeladapter.categorylistadapter.CategoryListAdapter
import com.task.channelapp.ui.main.channeladapter.episdoelistadapter.MediaListAdapter
import com.task.channelapp.utils.base.interfaces.OnItemClickListener
import com.task.channelapp.utils.extensions.loadImage


class ChannelListViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val mediaListAdapter: MediaListAdapter = MediaListAdapter(mutableListOf())
    private val categoryListAdapter: CategoryListAdapter = CategoryListAdapter(mutableListOf())
    private val maxItemsSize: Int = 6

    @SuppressLint("SetTextI18n")
    fun onBind(
        data: ChannelData,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemEpisodeBinding -> {
                mediaListAdapter.onItemClickListener = onItemClickListener
                mediaListAdapter.updateMediaListItems(data.episodes)
                binding.rvEpisodeList.adapter = mediaListAdapter
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
                        R.drawable.ic_mindvalley
                    )
                )
                mediaListAdapter.onItemClickListener =
                    getItemClickItemListener(data, position, onItemClickListener)
                mediaListAdapter.updateMediaListItems(
                    if (data.latestMedia.size > maxItemsSize) data.latestMedia.subList(
                        0,
                        maxItemsSize
                    ) else data.latestMedia
                )
                binding.rvChannelList.adapter = mediaListAdapter
            }

            is LayoutItemSeriesBinding -> {
                binding.tvTitle.text = data.title
                binding.tvCount.text = binding.tvCount.context.getString(
                    R.string.screen_main_display_text_series,
                    data.series.size.toString()
                )
                binding.ivChannelIcon.loadImage(
                    data.iconAsset,
                    ContextCompat.getDrawable(
                        binding.ivChannelIcon.context,
                        R.drawable.ic_mindvalley
                    )
                )
                mediaListAdapter.onItemClickListener =
                    getItemClickItemListener(data, position, onItemClickListener)
                mediaListAdapter.updateMediaListItems(
                    if (data.series.size > maxItemsSize) data.series.subList(
                        0,
                        maxItemsSize
                    ) else data.series
                )
                binding.rvSeriesList.adapter = mediaListAdapter
            }
            is LayoutItemCategoriesBinding -> {
                categoryListAdapter.onItemClickListener = onItemClickListener
                binding.rvCategoryList.adapter = categoryListAdapter
                categoryListAdapter.updateCategoryListItems(data.categories)
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