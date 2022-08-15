package com.task.channelapp.data.local.localservice

import com.task.channelapp.base.BaseTestCase
import com.task.channelapp.data.local.entities.CategoryEntity
import com.task.channelapp.data.local.entities.ChannelEntity
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ChannelLocalRepoTest : BaseTestCase() {
    // Subject under test
    lateinit var channelRepositoryLocal: ChannelLocalRepo

    // Use a fake Service to be injected into the Repo
    lateinit var localDao: ChannelLocalDao

    @Before
    fun setUp() {
        localDao = mockk()
    }

    @Test
    fun `get categories list success`() {
        //1- Mock calls
        runTest {
            val response = mockk<ArrayList<CategoryEntity>>()

            coEvery {
                localDao.getAllCategories()
            } returns response

            //2-Call
            channelRepositoryLocal = ChannelLocalRepo(localDao)
            val actual: List<CategoryEntity>? = channelRepositoryLocal.getCategories()
            //3-verify
            Assert.assertEquals(true, actual != null)
            coVerify { localDao.getAllCategories() }

        }
    }


    @Test
    fun `get categories list error`() {
        //1- Mock calls
        runTest {
            val response = null

            coEvery {
                localDao.getAllCategories()
            } returns response

            //2-Call
            channelRepositoryLocal = ChannelLocalRepo(localDao)
            val actual: List<CategoryEntity>? = channelRepositoryLocal.getCategories()
            //3-verify
            Assert.assertEquals(true, actual == null)
            coVerify { localDao.getAllCategories() }

        }
    }


    @Test
    fun `insert categories list success`() {
        //1- Mock calls
        runTest {
            val list = mockk<ArrayList<CategoryEntity>>()

            coEvery {
                localDao.insertAllCategories(list)
            } returns Unit

            //2-Call
            channelRepositoryLocal = ChannelLocalRepo(localDao)
            channelRepositoryLocal.insertCategories(list)
            //3-verify
            coVerify { localDao.insertAllCategories(list) }

        }
    }

    @Test
    fun `get channels list success`() {
        //1- Mock calls
        runTest {
            val response = mockk<ArrayList<ChannelEntity>>()

            coEvery {
                localDao.getAllChannels()
            } returns response

            //2-Call
            channelRepositoryLocal = ChannelLocalRepo(localDao)
            val actual: List<ChannelEntity>? = channelRepositoryLocal.getChannels()
            //3-verify
            Assert.assertEquals(true, actual != null)
            coVerify { localDao.getAllChannels() }

        }
    }


    @Test
    fun `get channels list error`() {
        //1- Mock calls
        runTest {
            val response = null

            coEvery {
                localDao.getAllChannels()
            } returns response

            //2-Call
            channelRepositoryLocal = ChannelLocalRepo(localDao)
            val actual: List<ChannelEntity>? = channelRepositoryLocal.getChannels()
            //3-verify
            Assert.assertEquals(true, actual == null)
            coVerify { localDao.getAllChannels() }

        }
    }

    @Test
    fun `insert channel list success`() {
        //1- Mock calls
        runTest {
            val list = mockk<ArrayList<ChannelEntity>>()

            coEvery {
                localDao.insertAllChannels(list)
            } returns Unit

            //2-Call
            channelRepositoryLocal = ChannelLocalRepo(localDao)
            channelRepositoryLocal.insertChannels(list)
            //3-verify
            coVerify { localDao.insertAllChannels(list) }

        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }

}