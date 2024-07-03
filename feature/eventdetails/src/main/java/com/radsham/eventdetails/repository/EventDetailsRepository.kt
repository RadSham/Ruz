package com.radsham.eventdetails.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.User
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class EventDetailsRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    suspend fun getCurrentUser(): User {
        return firebaseDatasource.getCurrentUser()
    }

    fun listenForEvent(eventId: String) = flow {
        while (true) {
            firebaseDatasource.getEvent(eventId).collect {
                emit(it)
            }
        }
    }

    suspend fun addParticipant(eventEntityId: String, participantUid: String) {
        firebaseDatasource.addParticipant(eventEntityId, participantUid)
    }
}