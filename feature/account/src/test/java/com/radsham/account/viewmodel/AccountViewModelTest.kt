package com.radsham.account.viewmodel

import android.content.Context
import app.cash.turbine.test
import com.radsham.account.repository.AccountRepository
import com.radsham.core_api.Result
import com.radsham.core_api.model.EventEntity
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
class AccountViewModelTest {

    private lateinit var viewModel: AccountViewModel
    private val context = Mockito.mock(Context::class.java)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val accountRepository: AccountRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AccountViewModel(context, accountRepository)
    }

    @Test
    fun `test getUserEventsList loading`() {
        runTest {
            val testLoadingResult = Result.Loading<String>("Loading")
            viewModel.viewState.test {
                Assert.assertEquals(testLoadingResult, expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test getUserEventsList success`() {
        runTest {
            val testEvent = EventEntity(name = "testEvent")
            whenever(accountRepository.getUserEventsList()).thenReturn(flowOf(listOf(testEvent)))
            viewModel.getUserEventsList()
            viewModel.viewState.test {
                Assert.assertEquals(Result.Success(listOf(testEvent)), expectMostRecentItem())
            }
        }
    }

    @Test
    fun `test getUserEventsList error`() {
        runTest {
            val expectedException = RuntimeException("Error")
            whenever(accountRepository.getUserEventsList()).thenThrow(expectedException)
            viewModel.getUserEventsList()
            viewModel.viewState.test {
                Assert.assertEquals(Result.Error<Exception>(expectedException), awaitItem())
            }
        }
    }
}