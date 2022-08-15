package com.task.channelapp.domain

import com.task.channelapp.base.BaseTestCase
import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import com.task.channelapp.data.local.entities.MediaEntity
import com.task.channelapp.data.local.localservice.ChannelDbService
import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.responsedtos.EpisodeResponse
import com.task.channelapp.data.remote.services.channelservice.ChannelApi
import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.*
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest : BaseTestCase() {
    // Subject under test
    lateinit var dataRepo: DataRepository

    // Use a fake Service to be injected into the Repo
    lateinit var localSource: ChannelDbService
    lateinit var remoteSource: ChannelApi

    @Before
    fun setUp() {
        localSource = mockk()
        remoteSource = mockk()
    }

    @Test
    fun `get categories api with local empty data success`() {
        //1- Mock calls
        runTest {
            val apiResponse = mockk<ApiResponse.Success<BaseResponse<CategoryResponse>>> {
                every { data } returns mockk {
                    every { data } returns mockk {
                        every { categories } returns listOf(mockk(relaxed = true))
                    }
                }
                every { code } returns 200
            }
            val categoryList =
                apiResponse.data.data?.categories?.map { CategoryData(it.name ?: "") } ?: listOf()
            coEvery {
                remoteSource.fetchCategories()
            } returns apiResponse
            coEvery {
                localSource.insertCategories(
                    categoryList.map { category ->
                        CategoryEntity(
                            name = category.name
                        )
                    }
                )
            } returns Unit

            coEvery {
                localSource.getCategories()
            } returns null

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<CategoryDTO> = dataRepo.getAllCategories(false)
            //3-verify
            Assert.assertEquals(
                1,
                (actual as DataResponse.Success<CategoryDTO>).data.categories.size
            )
            coVerify { remoteSource.fetchCategories() }
            coVerify { localSource.getCategories() }
            coVerify {
                localSource.insertCategories(
                    categoryList.map { category ->
                        CategoryEntity(
                            name = category.name
                        )
                    }
                )
            }
        }
    }

    @Test
    fun `get categories api with refresh data success`() {
        //1- Mock calls
        runTest {
            val apiResponse = mockk<ApiResponse.Success<BaseResponse<CategoryResponse>>> {
                every { data } returns mockk {
                    every { data } returns mockk {
                        every { categories } returns listOf(mockk(relaxed = true))
                    }
                }
                every { code } returns 200
            }
            val categoryList =
                apiResponse.data.data?.categories?.map { CategoryData(it.name ?: "") } ?: listOf()
            coEvery {
                remoteSource.fetchCategories()
            } returns apiResponse
            coEvery {
                localSource.insertCategories(
                    categoryList.map { category ->
                        CategoryEntity(
                            name = category.name
                        )
                    }
                )
            } returns Unit

            coEvery {
                localSource.getCategories()
            } returns listOf(mockk())

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<CategoryDTO> = dataRepo.getAllCategories(true)
            //3-verify
            Assert.assertEquals(
                1,
                (actual as DataResponse.Success<CategoryDTO>).data.categories.size
            )
            coVerify { remoteSource.fetchCategories() }
            coVerify { localSource.getCategories() }
            coVerify {
                localSource.insertCategories(
                    categoryList.map { category ->
                        CategoryEntity(
                            name = category.name
                        )
                    }
                )
            }
        }
    }


    @Test
    fun `get categories Local success`() {
        //1- Mock calls
        runTest {
            val response = arrayListOf(CategoryEntity("Category"))
            coEvery {
                localSource.getCategories()
            } returns response

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<CategoryDTO> = dataRepo.getAllCategories(false)
            //3-verify
            Assert.assertEquals(
                1,
                (actual as DataResponse.Success<CategoryDTO>).data.categories.size
            )
            coVerify { localSource.getCategories() }
        }
    }

    @Test
    fun `get categories api Error`() {
        //1- Mock calls
        runTest {
            val apiResponse = mockk<ApiResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { statusCode } returns 401
                    every { actualCode } returns "401"
                }
            }

            coEvery {
                remoteSource.fetchCategories()
            } returns apiResponse

            coEvery {
                localSource.getCategories()
            } returns null

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<CategoryDTO> = dataRepo.getAllCategories(false)
            //3-verify
            Assert.assertEquals(
                401,
                (actual as DataResponse.Error).error.code
            )
            coVerify { remoteSource.fetchCategories() }
            coVerify { localSource.getCategories() }

        }
    }

    @Test
    fun `get episode api with local empty data success`() {
        //1- Mock calls
        runTest {
            val apiResponse = mockk<ApiResponse.Success<BaseResponse<EpisodeResponse>>> {
                every { data } returns mockk {
                    every { data } returns mockk {
                        every { media } returns listOf(mockk(relaxed = true))
                    }
                }
                every { code } returns 200
            }
            val episodeList =
                apiResponse.data.data?.media?.map { media ->
                    MediaData(
                        id = media.id,
                        title = media.title ?: "",
                        type = media.type ?: "",
                        coverAsset = media.coverAsset?.url ?: "",
                        channel = ChannelData(title = media.channel?.title ?: "")
                    )
                } ?: listOf()
            coEvery {
                remoteSource.fetchEpisodes()
            } returns apiResponse
            coEvery {
                localSource.insertEpisodes(
                    episodeList.map { media ->
                        MediaEntity(
                            id = media.id,
                            title = media.title,
                            type = media.type,
                            coverAsset = media.coverAsset,
                            channel = ChannelEntity(title = media.channel.title)
                        )
                    }
                )
            } returns Unit

            coEvery {
                localSource.getEpisodes()
            } returns null

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<MediaDTO> = dataRepo.getAllNewEpisodes(false)
            //3-verify
            Assert.assertEquals(
                1,
                (actual as DataResponse.Success<MediaDTO>).data.media.size
            )
            coVerify { remoteSource.fetchEpisodes() }
            coVerify { localSource.getEpisodes() }
            coVerify {
                localSource.insertEpisodes(
                    episodeList.map { media ->
                        MediaEntity(
                            id = media.id,
                            title = media.title,
                            type = media.type,
                            coverAsset = media.coverAsset,
                            channel = ChannelEntity(title = media.channel.title)
                        )
                    }
                )
            }
        }
    }

    @Test
    fun `get episodes api with refresh data success`() {
        //1- Mock calls
        runTest {
            val apiResponse = mockk<ApiResponse.Success<BaseResponse<EpisodeResponse>>> {
                every { data } returns mockk {
                    every { data } returns mockk {
                        every { media } returns listOf(mockk(relaxed = true))
                    }
                }
                every { code } returns 200
            }
            val episodeList =
                apiResponse.data.data?.media?.map { media ->
                    MediaData(
                        id = media.id,
                        title = media.title ?: "",
                        type = media.type ?: "",
                        coverAsset = media.coverAsset?.url ?: "",
                        channel = ChannelData(title = media.channel?.title ?: "")
                    )
                } ?: listOf()
            coEvery {
                remoteSource.fetchEpisodes()
            } returns apiResponse
            coEvery {
                localSource.insertEpisodes(
                    episodeList.map { media ->
                        MediaEntity(
                            id = media.id,
                            title = media.title,
                            type = media.type,
                            coverAsset = media.coverAsset,
                            channel = ChannelEntity(title = media.channel.title)
                        )
                    }
                )
            } returns Unit

            coEvery {
                localSource.getEpisodes()
            } returns listOf(mockk())

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<MediaDTO> = dataRepo.getAllNewEpisodes(true)
            //3-verify
            Assert.assertEquals(
                1,
                (actual as DataResponse.Success<MediaDTO>).data.media.size
            )
            coVerify { remoteSource.fetchEpisodes() }
            coVerify { localSource.getEpisodes() }
            coVerify {
                localSource.insertEpisodes(
                    episodeList.map { media ->
                        MediaEntity(
                            id = media.id,
                            title = media.title,
                            type = media.type,
                            coverAsset = media.coverAsset,
                            channel = ChannelEntity(title = media.channel.title)
                        )
                    }
                )
            }
        }
    }

    @Test
    fun `get episode Local success`() {
        //1- Mock calls
        runTest {
            val response = arrayListOf(mockk<MediaEntity>(relaxed = true))
            coEvery {
                localSource.getEpisodes()
            } returns response

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<MediaDTO> = dataRepo.getAllNewEpisodes(false)
            //3-verify
            Assert.assertEquals(
                1,
                (actual as DataResponse.Success<MediaDTO>).data.media.size
            )
            coVerify { localSource.getEpisodes() }
        }
    }

    @Test
    fun `get episodes api Error`() {
        //1- Mock calls
        runTest {
            val apiResponse = mockk<ApiResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { statusCode } returns 401
                    every { actualCode } returns "401"
                }
            }

            coEvery {
                remoteSource.fetchEpisodes()
            } returns apiResponse

            coEvery {
                localSource.getEpisodes()
            } returns null

            //2-Call
            dataRepo = DataRepository(remoteSource, localSource)
            val actual: DataResponse<MediaDTO> = dataRepo.getAllNewEpisodes(false)
            //3-verify
            Assert.assertEquals(
                401,
                (actual as DataResponse.Error).error.code
            )
            coVerify { remoteSource.fetchEpisodes() }
            coVerify { localSource.getEpisodes() }
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}
