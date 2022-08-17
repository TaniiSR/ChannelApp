package com.task.channelapp.ui.main.channeladapter.categorylistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.task.channelapp.R
import com.task.channelapp.databinding.LayoutItemCategoryChildBinding
import com.task.channelapp.domain.dtos.CategoryData
import com.task.channelapp.ui.main.channeladapter.diffutils.CategoryDiffCallback
import com.task.channelapp.utils.base.BaseRecyclerAdapter

class CategoryListAdapter(
    private val list: MutableList<CategoryData>,
) : BaseRecyclerAdapter<CategoryData, CategoryListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): CategoryListItemViewHolder {
        return CategoryListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_item_category_child
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return LayoutItemCategoryChildBinding.inflate(layoutInflater, viewGroup, false)
    }

    fun updateCategoryListItems(rateList: List<CategoryData>) {
        val diffCallback = CategoryDiffCallback(list, rateList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        this.setList(rateList)
        diffResult.dispatchUpdatesTo(this)
    }
}