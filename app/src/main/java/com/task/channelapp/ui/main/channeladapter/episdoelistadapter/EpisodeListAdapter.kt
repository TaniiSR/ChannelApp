package com.task.channelapp.ui.main.channeladapter.episdoelistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemEpisodeChildBinding
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.ui.main.channeladapter.diffutils.MediaDiffCallback
import com.task.channelapp.utils.base.BaseRecyclerAdapter

class EpisodeListAdapter(
    private val list: MutableList<MediaData>,
) : BaseRecyclerAdapter<MediaData, EpisodeListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): EpisodeListItemViewHolder {
        return EpisodeListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_item_episode_child
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return LayoutItemEpisodeChildBinding.inflate(layoutInflater, viewGroup, false)
    }

    fun updateEpisodeListItems(rateList: List<MediaData>) {
        val diffCallback = MediaDiffCallback(list, rateList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        this.setList(rateList)
        diffResult.dispatchUpdatesTo(this)
    }
}