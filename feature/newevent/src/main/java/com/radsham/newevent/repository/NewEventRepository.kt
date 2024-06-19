package com.radsham.newevent.repository

import android.content.Context
import android.widget.Toast
import com.radsham.core_api.EventSendOnSuccessListener
import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.EventEntity
import javax.inject.Inject

class NewEventRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    suspend fun sendNewEvent(appContext: Context, eventEntity: EventEntity)  {
        firebaseDatasource.sendNewEvent(appContext, eventEntity, object : EventSendOnSuccessListener {
            override fun onComplete() {
                Toast.makeText(appContext, "Event successfully added", Toast.LENGTH_SHORT).show()
            }
            override fun onFail() {
                Toast.makeText(appContext, "Event adding failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}