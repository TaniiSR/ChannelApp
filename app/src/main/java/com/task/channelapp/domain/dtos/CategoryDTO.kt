package com.task.channelapp.domain.dtos

import com.task.channelapp.domain.base.BaseDataResponse

class CategoryDTO(
    val categories: List<CategoryData>
) : BaseDataResponse()