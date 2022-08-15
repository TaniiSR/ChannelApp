package com.task.channelapp.domain.interfaces

import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.CategoryDTO

interface ICategoryDataRepo {
    suspend fun getAllCategories(isRefresh: Boolean): DataResponse<CategoryDTO>
}