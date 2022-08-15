package com.task.channelapp.domain

import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.localservice.ChannelDbService
import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.services.channelservice.ChannelApi
import com.task.channelapp.domain.base.DataError
import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.CategoryDTO
import com.task.channelapp.domain.dtos.CategoryData
import com.task.channelapp.domain.interfaces.ICategoryDataRepo
import com.task.channelapp.domain.interfaces.IChannelDataRepo
import com.task.channelapp.domain.interfaces.IEpisodeDataRepo
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteRepository: ChannelApi,
    private val localRepository: ChannelDbService
) : ICategoryDataRepo, IChannelDataRepo, IEpisodeDataRepo {

    override suspend fun getAllCategories(isRefresh: Boolean): DataResponse<CategoryDTO> {
        val categories = localRepository.getCategories()
        return when {
            categories?.isNotEmpty() == true && !isRefresh -> {
                val currencyList = categories.map { entity ->
                    CategoryData(
                        name = entity.name
                    )
                }
                DataResponse.Success(data = CategoryDTO(currencyList))
            }
            else -> {
                when (val response: ApiResponse<BaseResponse<CategoryResponse>> =
                    remoteRepository.fetchCategories()) {
                    is ApiResponse.Success -> {
                        val categoryList =
                            response.data.data?.categories?.map { CategoryData(it.name ?: "") }
                                ?: listOf()
                        localRepository.insertCategories(categoryList.map { category ->
                            CategoryEntity(
                                name = category.name
                            )
                        })
                        DataResponse.Success(CategoryDTO(categoryList))
                    }
                    is ApiResponse.Error -> {
                        DataResponse.Error(
                            DataError(
                                response.error.statusCode,
                                response.error.message
                            )
                        )
                    }
                }
            }
        }
    }
}