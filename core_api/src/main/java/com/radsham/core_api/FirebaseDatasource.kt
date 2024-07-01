package com.radsham.core_api

import com.radsham.core_api.listener.EventCreateListener
import com.radsham.core_api.listener.UserCreateListener
import com.radsham.core_api.listener.UserSignInListener
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import kotlinx.coroutines.flow.Flow

interface FirebaseDatasource {
    suspend fun getUserUid(): String
    suspend fun getCurrentUser(): User
    suspend fun getAllEvents(): Flow<List<EventEntity>>
    suspend fun getEvent(eventId: String): Flow<EventEntity>
    suspend fun createNewEvent(eventEntity: EventEntity, eventCreateListener: EventCreateListener)
    suspend fun createNewUser(user: User, password: String, userCreateListener: UserCreateListener)
    suspend fun signInUser(email: String, password: String, userSignInListener: UserSignInListener)
    suspend fun getUserEventsList(): Flow<List<EventEntity>>
    suspend fun userSignOut()
}

