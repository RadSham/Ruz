package com.radsham.home.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.User
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {

    suspend fun userSignOut(){
        firebaseDatasource.userSignOut()
    }
    suspend fun getCurrentUser(): User {
        return firebaseDatasource.getCurrentUser()
    }

    fun listenForEvents() = flow {
        while (true) {
            firebaseDatasource.getAllEvents().collect {
                emit(it)
            }
        }
    }
}