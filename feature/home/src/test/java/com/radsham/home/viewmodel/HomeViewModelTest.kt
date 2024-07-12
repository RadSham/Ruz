package com.radsham.home.viewmodel

import android.content.Context
import app.cash.turbine.test
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val context = Mockito.mock(Context::class.java)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val homeRepository: HomeRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(context, homeRepository)
    }

    @Test
    fun `test fetchAllEvents loading`() {
        runTest {
            val testLoadingResult = Result.Loading<String>("Loading")
            viewModel.viewState.test {
                Assert.assertEquals(testLoadingResult, expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test fetchAllEvents success`() {
        runTest {
            val testEvent = EventEntity(name = "testEvent")
            whenever(homeRepository.listenForEvents()).thenReturn(flowOf(listOf(testEvent)))
            viewModel.fetchAllEvents()
            viewModel.viewState.test {
                Assert.assertEquals(Result.Success(listOf(testEvent)), expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test fetchAllEvents error`() {
        runTest {
            val expectedException = RuntimeException("Error")
            whenever(homeRepository.listenForEvents()).thenThrow(expectedException)
            viewModel.fetchAllEvents()
            viewModel.viewState.test {
                Assert.assertEquals(Result.Error<Exception>(expectedException), awaitItem())
            }
        }
    }
}