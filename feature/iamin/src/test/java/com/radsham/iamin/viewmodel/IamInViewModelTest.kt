package com.radsham.iamin.viewmodel

import android.content.Context
import app.cash.turbine.test
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
import com.radsham.iamin.repository.IamInRepository
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
class IamInViewModelTest {

    private lateinit var viewModel: IamInViewModel
    private val context = Mockito.mock(Context::class.java)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val iamInRepository: IamInRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = IamInViewModel(context, iamInRepository)
    }

    @Test
    fun `test listenForIamInEvents loading`() {
        runTest {
            val testLoadingResult = Result.Loading<String>("Loading")
            viewModel.viewState.test {
                Assert.assertEquals(testLoadingResult, expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test listenForIamInEvents success`() {
        runTest {
            val testEvent = EventEntity(name = "testEvent")
            whenever(iamInRepository.listenForIamInEvents()).thenReturn(flowOf(listOf(testEvent)))
            viewModel.fetchIamInEvents()
            viewModel.viewState.test {
                Assert.assertEquals(Result.Success(listOf(testEvent)), expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test listenForIamInEvents error`() {
        runTest {
            val expectedException = RuntimeException("Error")
            whenever(iamInRepository.listenForIamInEvents()).thenThrow(expectedException)
            viewModel.fetchIamInEvents()
            viewModel.viewState.test {
                Assert.assertEquals(Result.Error<Exception>(expectedException), awaitItem())
            }
        }
    }
}