package com.radsham.home

import com.radsham.network.FirebaseDatasource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {

    fun listenForEvents() = flow {
        while (true) {
            firebaseDatasource.getEventsList().collect {
                emit(it)
            }
        }
    }
}