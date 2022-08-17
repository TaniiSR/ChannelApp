package com.task.channelapp.ui.main

import com.task.channelapp.base.BaseTestCase
import com.task.channelapp.base.getOrAwaitValue
import com.task.channelapp.domain.base.DataResponse
import com.task.channelapp.domain.dtos.CategoryDTO
import com.task.channelapp.domain.dtos.ChannelDTO
import com.task.channelapp.domain.dtos.MediaDTO
import com.task.channelapp.domain.interfaces.ICategoryDataRepo
import com.task.channelapp.domain.interfaces.IChannelDataRepo
import com.task.channelapp.domain.interfaces.IEpisodeDataRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: MainVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var episodeRep: IEpisodeDataRepo
    lateinit var channelRepo: IChannelDataRepo
    lateinit var categoryRepo: ICategoryDataRepo

    @Before
    fun setUp() {
        episodeRep = mockk<IEpisodeDataRepo>()
        channelRepo = mockk<IChannelDataRepo>()
        categoryRepo = mockk<ICategoryDataRepo>()
    }

    @Test
    fun `get episodes, channels and categories data success`() {
        //1- Mock calls
        runTest {
            val episodeResponse = mockk<DataResponse.Success<MediaDTO>> {
                every { data } returns mockk {
                    every { media } returns listOf(mockk(), mockk())
                }
            }
            val channelResponse = mockk<DataResponse.Success<ChannelDTO>> {
                every { data } returns mockk {
                    every { channels } returns listOf(mockk(), mockk())
                }
            }
            val categoryResponse = mockk<DataResponse.Success<CategoryDTO>> {
                every { data } returns mockk {
                    every { categories } returns listOf(mockk(), mockk())
                }
            }

            coEvery {
                episodeRep.getAllNewEpisodes(true)
            } returns episodeResponse
            coEvery {
                channelRepo.getAllChannels(true)
            } returns channelResponse
            coEvery {
                categoryRepo.getAllCategories(true)
            } returns categoryResponse


            //2-Call
            viewModel = MainVM(
                episodeRep = episodeRep,
                channelRepo = channelRepo,
                categoryRepo = categoryRepo
            )
            viewModel.getDataFromRepos(true)

            //3-verify
            Assert.assertEquals(
                true,
                viewModel.channels.getOrAwaitValue().isNotEmpty()
            )
            Assert.assertEquals(
                true,
                viewModel.episodes.getOrAwaitValue().isNotEmpty()
            )
            Assert.assertEquals(
                true,
                viewModel.categories.getOrAwaitValue().isNotEmpty()
            )
            coVerify { episodeRep.getAllNewEpisodes(true) }
            coVerify { channelRepo.getAllChannels(true) }
            coVerify { categoryRepo.getAllCategories(true) }
        }
    }

    @Test
    fun `get episodes, channels data success and categories data error`() {
        //1- Mock calls
        runTest {
            val episodeResponse = mockk<DataResponse.Success<MediaDTO>> {
                every { data } returns mockk {
                    every { media } returns listOf(mockk(), mockk())
                }
            }
            val channelResponse = mockk<DataResponse.Success<ChannelDTO>> {
                every { data } returns mockk {
                    every { channels } returns listOf(mockk(), mockk())
                }
            }
            val categoryResponse = mockk<DataResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { code } returns 401
                }
            }

            coEvery {
                episodeRep.getAllNewEpisodes(true)
            } returns episodeResponse
            coEvery {
                channelRepo.getAllChannels(true)
            } returns channelResponse
            coEvery {
                categoryRepo.getAllCategories(true)
            } returns categoryResponse


            //2-Call
            viewModel = MainVM(
                episodeRep = episodeRep,
                channelRepo = channelRepo,
                categoryRepo = categoryRepo
            )
            viewModel.getDataFromRepos(true)

            //3-verify
            Assert.assertEquals(
                true,
                viewModel.channels.getOrAwaitValue().isNotEmpty()
            )
            Assert.assertEquals(
                true,
                viewModel.episodes.getOrAwaitValue().isNotEmpty()
            )
            Assert.assertEquals(
                true,
                viewModel.categories.getOrAwaitValue() == null
            )
            coVerify { episodeRep.getAllNewEpisodes(true) }
            coVerify { channelRepo.getAllChannels(true) }
            coVerify { categoryRepo.getAllCategories(true) }
        }
    }


    @Test
    fun `get episodes data success and channels, categories data error`() {
        //1- Mock calls
        runTest {
            val episodeResponse = mockk<DataResponse.Success<MediaDTO>> {
                every { data } returns mockk {
                    every { media } returns listOf(mockk(), mockk())
                }
            }
            val channelResponse = mockk<DataResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { code } returns 401
                }
            }

            val categoryResponse = mockk<DataResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { code } returns 401
                }
            }

            coEvery {
                episodeRep.getAllNewEpisodes(true)
            } returns episodeResponse
            coEvery {
                channelRepo.getAllChannels(true)
            } returns channelResponse
            coEvery {
                categoryRepo.getAllCategories(true)
            } returns categoryResponse


            //2-Call
            viewModel = MainVM(
                episodeRep = episodeRep,
                channelRepo = channelRepo,
                categoryRepo = categoryRepo
            )
            viewModel.getDataFromRepos(true)

            //3-verify
            Assert.assertEquals(
                true,
                viewModel.episodes.getOrAwaitValue().isNotEmpty()
            )
            Assert.assertEquals(
                true,
                viewModel.categories.getOrAwaitValue() == null
            )
            Assert.assertEquals(
                true,
                viewModel.channels.getOrAwaitValue() == null
            )
            coVerify { episodeRep.getAllNewEpisodes(true) }
            coVerify { channelRepo.getAllChannels(true) }
            coVerify { categoryRepo.getAllCategories(true) }
        }
    }

    @Test
    fun `get episodes, channels and categories data error`() {
        //1- Mock calls
        runTest {
            val episodeResponse = mockk<DataResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { code } returns 401
                }
            }

            val channelResponse = mockk<DataResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { code } returns 401
                }
            }

            val categoryResponse = mockk<DataResponse.Error> {
                every { error } returns mockk {
                    every { message } returns "Error"
                    every { code } returns 401
                }
            }

            coEvery {
                episodeRep.getAllNewEpisodes(true)
            } returns episodeResponse
            coEvery {
                channelRepo.getAllChannels(true)
            } returns channelResponse
            coEvery {
                categoryRepo.getAllCategories(true)
            } returns categoryResponse


            //2-Call
            viewModel = MainVM(
                episodeRep = episodeRep,
                channelRepo = channelRepo,
                categoryRepo = categoryRepo
            )
            viewModel.getDataFromRepos(true)

            //3-verify
            Assert.assertEquals(
                true,
                viewModel.channels.getOrAwaitValue() == null
            )
            Assert.assertEquals(
                true,
                viewModel.episodes.getOrAwaitValue() == null
            )
            Assert.assertEquals(
                true,
                viewModel.categories.getOrAwaitValue() == null
            )
            coVerify { episodeRep.getAllNewEpisodes(true) }
            coVerify { channelRepo.getAllChannels(true) }
            coVerify { categoryRepo.getAllCategories(true) }
        }
    }

}