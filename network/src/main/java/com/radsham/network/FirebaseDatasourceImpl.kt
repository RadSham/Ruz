package com.radsham.network

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.radsham.core_api.model.EventEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseDatasourceImpl @Inject constructor() : FirebaseDatasource {
    private var firebaseDatabase = Firebase.database.reference
    override suspend fun getEventsList(): Flow<List<EventEntity>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val eventEntityList = dataSnapshot.children.find {
                    it.key == "events"
                }!!.children.map {
                    it.getValue<EventEntity>()!!
                }
                trySend(eventEntityList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyLog", "Failed to read value.", error.toException())
            }
        }
        firebaseDatabase.addValueEventListener(postListener)
        awaitClose { firebaseDatabase.removeEventListener(postListener) }
    }
}