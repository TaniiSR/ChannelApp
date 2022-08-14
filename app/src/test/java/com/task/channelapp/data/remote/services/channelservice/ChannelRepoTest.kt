package com.task.channelapp.data.remote.services.channelservice

import com.task.channelapp.base.BaseTestCase
import com.task.channelapp.data.remote.baseclient.ApiResponse
import com.task.channelapp.data.remote.baseclient.models.BaseResponse
import com.task.channelapp.data.remote.responsedtos.CategoryResponse
import com.task.channelapp.data.remote.responsedtos.ChannelResponse
import com.task.channelapp.data.remote.responsedtos.EpisodeResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class ChannelRepoTest : BaseTestCase() {
    // Subject under test
    lateinit var channelRepo: ChannelRepo

    // Use a fake Service to be injected into the Repo
    lateinit var service: ChannelRetroApi

    @Before
    fun setUp() {
        service = mockk()
    }

    @Test
    fun `get Categories api success`() {
        //1- Mock calls
        runTest {
            val response = mockk<Response<BaseResponse<CategoryResponse>>> {
                every { isSuccessful } returns true
                every { code() } returns 200
                every { body() } returns mockk {
                    every { data } returns mockk {
                        every { categories } returns listOf(mockk(), mockk())
                    }
                }
            }
            coEvery {
                service.fetchCategories()
            } returns response

            //2-Call
            channelRepo = ChannelRepo(service)
            val actual: ApiResponse<BaseResponse<CategoryResponse>> = channelRepo.fetchCategories()
            //3-verify
            Assert.assertEquals(200, (actual as ApiResponse.Success).code)
            Assert.assertEquals(
                true,
                (actual as ApiResponse.Success).data.data?.categories?.isNotEmpty()
            )
            coVerify { service.fetchCategories() }
        }
    }

    @Test
    fun `get Categories api Error`() {
        //1- Mock calls
        runTest {
            val response = mockk<Response<BaseResponse<CategoryResponse>>> {
                every { isSuccessful } returns false
                every { code() } returns 401
                every { message() } returns "Error"
            }
            coEvery {
                service.fetchCategories()
            } returns response

            //2-Call
            channelRepo = ChannelRepo(service)
            val actual: ApiResponse<BaseResponse<CategoryResponse>> = channelRepo.fetchCategories()
            //3-verify
            Assert.assertEquals(401, (actual as ApiResponse.Error).error.statusCode)
            coVerify { service.fetchCategories() }
        }
    }

    @Test
    fun `get episodes api success`() {
        //1- Mock calls
        runTest {
            val response = mockk<Response<BaseResponse<EpisodeResponse>>> {
                every { isSuccessful } returns true
                every { code() } returns 200
                every { body() } returns mockk {
                    every { data } returns mockk {
                        every { media } returns listOf(mockk(), mockk())
                    }
                }
            }
            coEvery {
                service.fetchEpisodes()
            } returns response

            //2-Call
            channelRepo = ChannelRepo(service)
            val actual: ApiResponse<BaseResponse<EpisodeResponse>> = channelRepo.fetchEpisodes()
            //3-verify
            Assert.assertEquals(200, (actual as ApiResponse.Success).code)
            Assert.assertEquals(
                true,
                (actual as ApiResponse.Success).data.data?.media?.isNotEmpty()
            )
            coVerify { service.fetchEpisodes() }
        }
    }


    @Test
    fun `get Episodes api Error`() {
        //1- Mock calls
        runTest {
            val response = mockk<Response<BaseResponse<EpisodeResponse>>> {
                every { isSuccessful } returns false
                every { code() } returns 401
                every { message() } returns "Error"
            }
            coEvery {
                service.fetchEpisodes()
            } returns response

            //2-Call
            channelRepo = ChannelRepo(service)
            val actual: ApiResponse<BaseResponse<EpisodeResponse>> = channelRepo.fetchEpisodes()
            //3-verify
            Assert.assertEquals(401, (actual as ApiResponse.Error).error.statusCode)
            coVerify { service.fetchEpisodes() }
        }
    }

    @Test
    fun `get channel api success`() {
        //1- Mock calls
        runTest {
            val response = mockk<Response<BaseResponse<ChannelResponse>>> {
                every { isSuccessful } returns true
                every { code() } returns 200
                every { body() } returns mockk {
                    every { data } returns mockk {
                        every { channels } returns listOf(mockk(), mockk())
                    }
                }
            }
            coEvery {
                service.fetchChannels()
            } returns response

            //2-Call
            channelRepo = ChannelRepo(service)
            val actual: ApiResponse<BaseResponse<ChannelResponse>> = channelRepo.fetchChannels()
            //3-verify
            Assert.assertEquals(200, (actual as ApiResponse.Success).code)
            Assert.assertEquals(
                true,
                (actual as ApiResponse.Success).data.data?.channels?.isNotEmpty()
            )
            coVerify { service.fetchChannels() }
        }
    }

    @Test
    fun `get Channels api Error`() {
        //1- Mock calls
        runTest {
            val response = mockk<Response<BaseResponse<ChannelResponse>>> {
                every { isSuccessful } returns false
                every { code() } returns 401
                every { message() } returns "Error"
            }
            coEvery {
                service.fetchChannels()
            } returns response

            //2-Call
            channelRepo = ChannelRepo(service)
            val actual: ApiResponse<BaseResponse<ChannelResponse>> = channelRepo.fetchChannels()
            //3-verify
            Assert.assertEquals(401, (actual as ApiResponse.Error).error.statusCode)
            coVerify { service.fetchEpisodes() }
        }
    }

}