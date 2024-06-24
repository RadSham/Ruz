package com.radsham.core_api_impl

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.storage.storage
import com.radsham.core_api.EventCreateListener
import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.EventEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseDatasourceImpl @Inject constructor() : FirebaseDatasource {
    private var firebaseDatabaseRef = Firebase.database.reference
    private val firebaseStorageRef = Firebase.storage.reference

    override suspend fun getAllEvents(): Flow<List<EventEntity>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val eventEntityList = dataSnapshot.children.find {
                    it.key == "events"
                }?.children?.map {
                    it.getValue<EventEntity>()!!
                }
                if (eventEntityList != null) {
                    trySend(eventEntityList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyLog", "Failed to read value.", error.toException())
            }
        }
        firebaseDatabaseRef.addValueEventListener(postListener)
        awaitClose { firebaseDatabaseRef.removeEventListener(postListener) }
    }

    override suspend fun getEvent(eventId: String): Flow<EventEntity> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val eventEntity: EventEntity? = dataSnapshot.children.find {
                    it.key == "events"
                }?.child(eventId)?.getValue<EventEntity>()
                if (eventEntity != null) {
                    trySend(eventEntity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyLog", "Failed to read value.", error.toException())
            }
        }
        firebaseDatabaseRef.addValueEventListener(postListener)
        awaitClose { firebaseDatabaseRef.removeEventListener(postListener) }
    }

    override suspend fun createNewEvent(
        context: Context,
        eventEntity: EventEntity,
        eventCreateListener: EventCreateListener
    ) {
        if (eventEntity.imageUri == "") {
            sendEventToDatabase(eventEntity, eventCreateListener)
        } else {
            val imageRef = firebaseStorageRef.child("images/${eventEntity.id}.jpg")
            val uploadTask = imageRef.putFile(eventEntity.imageUri.toUri())
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    eventEntity.imageUri = downloadUri.toString()
                    sendEventToDatabase(eventEntity, eventCreateListener)
                } else {
                    eventCreateListener.onFailure()
                }
            }
        }
    }

    private fun sendEventToDatabase(
        eventEntity: EventEntity,
        eventCreateListener: EventCreateListener
    ) {
        firebaseDatabaseRef.child("events").child(eventEntity.id)
            .setValue(eventEntity).addOnSuccessListener {
                eventCreateListener.onSuccess()
                Log.d("MyLog", "Success $eventEntity")
            }.addOnFailureListener {
                eventCreateListener.onFailure()
            }
    }
}