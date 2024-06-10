package com.radsham.network

import com.radsham.common.model.EventEntity
import kotlinx.coroutines.flow.Flow


interface FirebaseDatasource {
    suspend fun getEventsList(): Flow<List<EventEntity>>
}

