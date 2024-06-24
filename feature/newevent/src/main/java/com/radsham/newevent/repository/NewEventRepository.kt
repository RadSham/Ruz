package com.radsham.newevent.repository

import android.content.Context
import android.widget.Toast
import com.radsham.core_api.EventCreateListener
import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.EventEntity
import javax.inject.Inject

class NewEventRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    suspend fun createNewEvent(appContext: Context, eventEntity: EventEntity)  {
        firebaseDatasource.createNewEvent(appContext, eventEntity, object : EventCreateListener {
            override fun onSuccess() {
                Toast.makeText(appContext, "Event successfully created", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure() {
                Toast.makeText(appContext, "Event adding failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}