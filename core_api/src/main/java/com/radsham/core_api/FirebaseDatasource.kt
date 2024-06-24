package com.radsham.core_api

import android.content.Context
import com.radsham.core_api.model.EventEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseDatasource {
    suspend fun getAllEvents(): Flow<List<EventEntity>>
    suspend fun getEvent(eventId: String): Flow<EventEntity>
    suspend fun createNewEvent(
        context: Context,
        eventEntity: EventEntity,
        eventCreateListener: EventCreateListener
    )
}

