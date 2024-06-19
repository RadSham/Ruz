package com.radsham.core_api

import android.content.Context
import com.radsham.core_api.model.EventEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseDatasource {
    suspend fun getEventsList(): Flow<List<EventEntity>>
    suspend fun sendNewEvent(
        context: Context,
        eventEntity: EventEntity,
        eventSendOnSuccessListener: EventSendOnSuccessListener
    )
}

