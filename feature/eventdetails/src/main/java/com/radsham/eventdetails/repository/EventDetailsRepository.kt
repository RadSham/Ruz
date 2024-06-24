package com.radsham.eventdetails.repository

import com.radsham.core_api.FirebaseDatasource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class EventDetailsRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    fun listenForEvent(eventId: String) = flow {
        while (true) {
            firebaseDatasource.getEvent(eventId).collect {
                emit(it)
            }
        }
    }
}