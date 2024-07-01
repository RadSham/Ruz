package com.radsham.newevent.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.listener.EventCreateListener
import com.radsham.core_api.model.EventEntity
import javax.inject.Inject

class NewEventRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    suspend fun createNewEvent(eventEntity: EventEntity, eventCreateListener: EventCreateListener) {
        firebaseDatasource.createNewEvent(
            eventEntity.apply { this.uid = firebaseDatasource.getUserUid() },
            eventCreateListener
        )
    }
}