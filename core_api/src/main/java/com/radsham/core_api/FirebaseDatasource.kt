package com.radsham.core_api

import com.radsham.core_api.model.EventEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseDatasource {
    suspend fun getEventsList(): Flow<List<EventEntity>>
}

