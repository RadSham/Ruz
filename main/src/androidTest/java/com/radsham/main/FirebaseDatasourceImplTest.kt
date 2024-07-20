package com.radsham.main

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.listener.EventCreateListener
import com.radsham.core_api.listener.UserCreateListener
import com.radsham.core_api.listener.UserSignInListener
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseDatasourceImplTest @Inject constructor() : FirebaseDatasource {
    override suspend fun getCurrentUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEvents(): Flow<List<EventEntity>> = flow {
        emit(listOf(EventEntity(name = "testEventEntity1"), EventEntity(name = "testEventEntity2")))
    }

    override suspend fun getEvent(eventId: String): Flow<EventEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewEvent(
        eventEntity: EventEntity,
        eventCreateListener: EventCreateListener
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun createNewUser(
        user: User,
        password: String,
        userCreateListener: UserCreateListener
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun signInUser(
        email: String,
        password: String,
        userSignInListener: UserSignInListener
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserEventsList(): Flow<List<EventEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun userSignOut() {
        TODO("Not yet implemented")
    }

    override suspend fun addParticipant(eventEntityId: String, participantUid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun excludeParticipant(eventEntityId: String, participantUid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(deleteEventId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getIamInEvents(): Flow<List<EventEntity>> {
        TODO("Not yet implemented")
    }
}