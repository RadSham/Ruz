package com.radsham.newevent.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.listener.EventCreateListener
import com.radsham.core_api.model.EventEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class NewEventRepositoryTest {

    private lateinit var newEventRepository: NewEventRepository
    private val testDispatcher = UnconfinedTestDispatcher()
    private val firebaseDatasource: FirebaseDatasource = mock()
    private val eventCreateListener: EventCreateListener = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        newEventRepository = NewEventRepository(firebaseDatasource)
    }

    @Test
    fun `test createNewEvent successful`() {
        runTest {
            val testEvent = EventEntity(name = "testEvent")
            whenever(firebaseDatasource.createNewEvent(testEvent, eventCreateListener)).doAnswer {
                eventCreateListener.onSuccess()
            }
            val expected = eventCreateListener.onSuccess()
            val actual = newEventRepository.createNewEvent(testEvent, eventCreateListener)
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun `test createNewEvent failed`() {
        runTest {
            val testEvent = EventEntity(name = "testEvent")
            val failedString = "testFailed"
            whenever(firebaseDatasource.createNewEvent(testEvent, eventCreateListener)).doAnswer {
                eventCreateListener.onFailure(failedString)
            }
            val expected = eventCreateListener.onFailure(failedString)
            val actual = newEventRepository.createNewEvent(testEvent, eventCreateListener)
            Assert.assertEquals(expected, actual)
        }
    }
}