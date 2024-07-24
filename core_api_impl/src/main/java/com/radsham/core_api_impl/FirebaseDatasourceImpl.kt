package com.radsham.core_api_impl

import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.storage.storage
import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.listener.EventCreateListener
import com.radsham.core_api.listener.UserCreateListener
import com.radsham.core_api.listener.UserSignInListener
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseDatasourceImpl @Inject constructor() : FirebaseDatasource {
    private var firebaseDatabaseRef = Firebase.database.reference
    private val firebaseStorageRef = Firebase.storage.reference
    private var firebaseAuth = Firebase.auth

    override suspend fun getCurrentUser(): User {
        val user = User(
            firebaseAuth.currentUser?.uid.toString(),
            firebaseAuth.currentUser?.displayName.toString(),
            firebaseAuth.currentUser?.email.toString()
        )
        return user
    }

    override suspend fun getAllEvents(): Flow<List<EventEntity>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val eventEntityList = dataSnapshot.child("events").children.map {
                    it.getValue<EventEntity>()!!
                }
                trySend(eventEntityList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyLog", "Failed to read value.", error.toException())
            }
        }
        firebaseDatabaseRef.addValueEventListener(postListener)
        awaitClose { firebaseDatabaseRef.removeEventListener(postListener) }
    }

    override suspend fun getIamInEvents(): Flow<List<EventEntity>> =
        callbackFlow {
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val eventEntityList = dataSnapshot.child("events").children.map {
                        it.getValue<EventEntity>()!!
                    }.filter { it.participants.containsKey(firebaseAuth.currentUser?.uid.toString()) }
                    trySend(eventEntityList)
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
                val eventEntity: EventEntity? = dataSnapshot.child("events").child(eventId).getValue<EventEntity>()
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
        eventEntity: EventEntity, eventCreateListener: EventCreateListener,
    ) {
        firebaseAuth.currentUser?.reload()
        if (firebaseAuth.currentUser?.isEmailVerified == true) {
            eventEntity.apply { this.uid = firebaseAuth.uid.toString() }
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
                        eventCreateListener.onFailure(task.exception?.message)
                    }
                }
            }
        } else {
            eventCreateListener.onFailure("Verification email has been sent to ${firebaseAuth.currentUser?.email}. Please verify your account")
            firebaseAuth.currentUser?.sendEmailVerification()
        }
    }

    private fun sendEventToDatabase(
        eventEntity: EventEntity, eventCreateListener: EventCreateListener,
    ) {
        firebaseDatabaseRef.child("events").child(eventEntity.id).setValue(eventEntity)
            .addOnSuccessListener {
                eventCreateListener.onSuccess()
            }.addOnFailureListener {
                eventCreateListener.onFailure(it.message)
            }
    }

    override suspend fun createNewUser(
        user: User,
        password: String,
        userCreateListener: UserCreateListener,
    ) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.sendEmailVerification()
                    firebaseAuth.currentUser!!.updateProfile(userProfileChangeRequest {
                        displayName = user.nickname
                    }).addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            userCreateListener.onSuccess(user)
                        }
                    }
                } else {
                    userCreateListener.onFailure(task.exception?.message)
                }
            }
    }

    override suspend fun signInUser(
        email: String,
        password: String,
        userSignInListener: UserSignInListener,
    ) {
        firebaseAuth.signOut()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userSignInListener.onSuccess()
                } else {
                    userSignInListener.onFailure(task.exception?.message)
                }
            }
    }

    override suspend fun getUserEventsList(): Flow<List<EventEntity>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userEventEntityList = dataSnapshot.child("events").children.map {
                    it.getValue<EventEntity>()!!
                }.filter { it.uid == firebaseAuth.uid.toString() }

                trySend(userEventEntityList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MyLog", "Failed to read value.", error.toException())
            }
        }
        firebaseDatabaseRef.addValueEventListener(postListener)
        awaitClose { firebaseDatabaseRef.removeEventListener(postListener) }
    }

    override suspend fun userSignOut() {
        firebaseAuth.signOut()
    }

    //TODO: add listener
    override suspend fun addParticipant(eventEntityId: String, participantUid: String) {
        firebaseDatabaseRef.child("events").child(eventEntityId).child("participants")
            .child(participantUid).setValue(participantUid)
            .addOnSuccessListener {
                Log.d("MyLog", "addParticipant")
            }.addOnFailureListener {
                Log.d("MyLog", "addParticipant OnFailure $it")
            }
    }

    //TODO: add listener
    override suspend fun excludeParticipant(eventEntityId: String, participantUid: String) {
        firebaseDatabaseRef.child("events").child(eventEntityId).child("participants")
            .child(participantUid).removeValue()
            .addOnSuccessListener {
                Log.d("MyLog", "excludeParticipant")
            }.addOnFailureListener {
                Log.d("MyLog", "excludeParticipant OnFailure $it")
            }
    }

    //TODO: add listener
    override suspend fun deleteEvent(deleteEventId: String) {
        firebaseDatabaseRef.child("events").child(deleteEventId).removeValue()
            .addOnSuccessListener {
                Log.d("MyLog", "deleteEvent")
            }.addOnFailureListener {
                Log.d("MyLog", "deleteEvent OnFailure $it")
            }
    }
}