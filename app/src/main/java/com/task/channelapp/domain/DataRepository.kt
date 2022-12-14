package com.task.channelapp.domain

import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity
import com.task.channelapp.data.local.localservice.ChannelDbService
import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.responsedtos.ChannelResponse
import com.task.channelapp.data.remote.responsedtos.EpisodeResponse
import com.task.channelapp.data.remote.services.channelservice.ChannelApi
import com.task.channelapp.domain.base.DataError
import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.*
import com.task.channelapp.domain.dtos.sealed.MediaType
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

    override suspend fun getAllNewEpisodes(isRefresh: Boolean): DataResponse<MediaDTO> {
        val episodes = localRepository.getEpisodes()
        return when {
            episodes?.isNotEmpty() == true && !isRefresh -> {
                val episodeList = episodes.map { entity ->
                    MediaData(
                        id = entity.id,
                        title = entity.title ?: "",
                        type = entity.type ?: "",
                        coverAsset = entity.coverAsset ?: "",
                        mediaType = MediaType.Episode,
                        channel = ChannelData(title = entity.channel?.title ?: "")
                    )
                }
                DataResponse.Success(data = MediaDTO(episodeList))
            }
            else -> {
                when (val response: ApiResponse<BaseResponse<EpisodeResponse>> =
                    remoteRepository.fetchEpisodes()) {
                    is ApiResponse.Success -> {
                        val episodeList =
                            response.data.data?.media?.map {
                                MediaData(
                                    id = it.id,
                                    title = it.title ?: "",
                                    type = it.type ?: "",
                                    coverAsset = it.coverAsset?.url ?: "",
                                    mediaType = MediaType.Episode,
                                    channel = ChannelData(title = it.channel?.title ?: "")
                                )
                            }
                                ?: listOf()
                        localRepository.insertEpisodes(episodeList.map { media ->
                            MediaEntity(
                                id = media.id,
                                title = media.title,
                                type = media.type,
                                coverAsset = media.coverAsset,
                                channel = ChannelEntity(title = media.channel.title)
                            )
                        })
                        DataResponse.Success(MediaDTO(episodeList))
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

    override suspend fun getAllChannels(isRefresh: Boolean): DataResponse<ChannelDTO> {
        val channels = localRepository.getChannels()
        return when {
            channels?.isNotEmpty() == true && !isRefresh -> {
                val channelList = channels.map { entity ->
                    ChannelData(
                        id = entity.id,
                        title = entity.title ?: "",
                        mediaCount = entity.mediaCount ?: -1,
                        coverAsset = entity.coverAsset ?: "",
                        iconAsset = entity.iconAsset ?: "",
                        latestMedia = entity.latestMedia?.map { media ->
                            MediaData(
                                id = media.id,
                                title = media.title ?: "",
                                type = media.type ?: "",
                                mediaType = MediaType.Channel,
                                coverAsset = media.coverAsset ?: ""
                            )
                        } ?: listOf(),
                        series = entity.series?.map { media ->
                            MediaData(
                                id = media.id,
                                title = media.title ?: "",
                                type = media.type ?: "",
                                mediaType = MediaType.Series,
                                coverAsset = media.coverAsset ?: "",
                                channel = ChannelData(title = media.channel?.title ?: "")
                            )
                        } ?: listOf()
                    )
                }
                DataResponse.Success(data = ChannelDTO(channelList))
            }
            else -> {
                when (val response: ApiResponse<BaseResponse<ChannelResponse>> =
                    remoteRepository.fetchChannels()) {
                    is ApiResponse.Success -> {
                        val channels =
                            response.data.data?.channels?.map { channel ->
                                ChannelData(
                                    id = channel.id,
                                    title = channel.title ?: "",
                                    mediaCount = channel.mediaCount ?: -1,
                                    coverAsset = channel.coverAsset?.url ?: "",
                                    iconAsset = channel.iconAsset?.thumbnailUrl ?: "",
                                    latestMedia = channel.latestMedia?.map { media ->
                                        MediaData(
                                            id = media.id,
                                            title = media.title ?: "",
                                            type = media.type ?: "",
                                            mediaType = MediaType.Channel,
                                            coverAsset = media.coverAsset?.url ?: ""
                                        )
                                    } ?: listOf(),
                                    series = channel.series?.map { media ->
                                        MediaData(
                                            id = media.id,
                                            title = media.title ?: "",
                                            type = media.type ?: "",
                                            mediaType = MediaType.Series,
                                            coverAsset = media.coverAsset?.url ?: "",
                                            channel = ChannelData(
                                                title = media.channel?.title ?: ""
                                            )
                                        )
                                    } ?: listOf()
                                )
                            } ?: listOf()
                        val entityList = channels.map { channel ->
                            ChannelEntity(
                                id = channel.id,
                                title = channel.title,
                                mediaCount = channel.mediaCount,
                                coverAsset = channel.coverAsset,
                                iconAsset = channel.iconAsset,
                                series = channel.series.map { media ->
                                    MediaEntity(
                                        id = media.id,
                                        title = media.title,
                                        type = media.type,
                                        coverAsset = media.coverAsset,
                                        channel = ChannelEntity(title = media.channel.title)
                                    )
                                },
                                latestMedia = channel.latestMedia.map { media ->
                                    MediaEntity(
                                        id = media.id,
                                        title = media.title,
                                        type = media.type,
                                        coverAsset = media.coverAsset
                                    )
                                }
                            )
                        }

                        localRepository.insertChannels(entityList)
                        DataResponse.Success(ChannelDTO(channels))
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