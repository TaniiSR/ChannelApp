package com.task.channelapp.ui.main.channeladapter.categorylistadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.channelapp.databinding.LayoutItemCategoryChildBinding
import com.task.channelapp.domain.dtos.CategoryData
import com.task.channelapp.utils.base.interfaces.OnItemClickListener


class CategoryListItemViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(
        data: CategoryData,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemCategoryChildBinding ->
                handleCategoryBinding(data, position, onItemClickListener, binding)
        }
    }

    private fun handleCategoryBinding(
        data: CategoryData,
        position: Int,
        onItemClickListener: OnItemClickListener?,
        binding: LayoutItemCategoryChildBinding
    ) {
        binding.tvTitle.text = data.name
        binding.clMain.setOnClickListener {
            onItemClickListener?.onItemClick(it, data, position)
        }
    }

}