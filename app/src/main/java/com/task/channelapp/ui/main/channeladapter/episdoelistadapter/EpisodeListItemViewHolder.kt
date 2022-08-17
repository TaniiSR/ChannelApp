package com.task.channelapp.ui.main.channeladapter.episdoelistadapter

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemEpisodeChildBinding
import com.task.channelapp.domain.dtos.MediaData
import com.task.channelapp.utils.base.interfaces.OnItemClickListener
import com.task.channelapp.utils.extensions.loadImage


class EpisodeListItemViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(
        data: MediaData,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemEpisodeChildBinding -> {
                binding.ivImage.loadImage(
                    data.coverAsset,
                    ContextCompat.getDrawable(
                        binding.ivImage.context,
                        R.drawable.ic_launcher_background
                    )
                )
                binding.tvTitle.text = data.title
                binding.tvChannelName.text = data.channel.title
                binding.clMain.setOnClickListener {
                    onItemClickListener?.onItemClick(it, data, position)
                }
            }
        }
    }
}