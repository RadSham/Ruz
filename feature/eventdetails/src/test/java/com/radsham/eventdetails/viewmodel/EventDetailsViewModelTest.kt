package com.radsham.iamin.viewmodel

import android.content.Context
import app.cash.turbine.test
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.eventdetails.repository.EventDetailsRepository
import com.radsham.eventdetails.viewmodel.EventDetailsViewModel
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
class EventDetailsViewModelTest {

    private lateinit var viewModel: EventDetailsViewModel
    private val context = Mockito.mock(Context::class.java)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val eventDetailsRepository: EventDetailsRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = EventDetailsViewModel(context, eventDetailsRepository)
    }

    @Test
    fun `test listenForEvent loading`() {
        runTest {
            val testLoadingResult = Result.Loading<String>("Loading")
            viewModel.viewState.test {
                Assert.assertEquals(testLoadingResult, expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test listenForEvent success`() {
        runTest {
            val testEvent = EventEntity(name = "testEvent")
            val testEventId = "0"
            whenever(eventDetailsRepository.listenForEvent(testEventId)).thenReturn(flowOf(testEvent))
            viewModel.fetchEvent(testEventId)
            viewModel.viewState.test {
                Assert.assertEquals(Result.Success(testEvent), expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test listenForEvent error`() {
        runTest {
            val testEventId = "0"
            val expectedException = RuntimeException("Error")
            whenever(eventDetailsRepository.listenForEvent(testEventId)).thenThrow(expectedException)
            viewModel.fetchEvent(testEventId)
            viewModel.viewState.test {
                Assert.assertEquals(Result.Error<Exception>(expectedException), awaitItem())
            }
        }
    }
}