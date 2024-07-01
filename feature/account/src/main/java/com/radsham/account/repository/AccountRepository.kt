package com.radsham.account.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.User
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {

    suspend fun getCurrentUser(): User {
        return firebaseDatasource.getCurrentUser()
    }

    suspend fun getUserEventsList() = flow {
        while (true) {
            firebaseDatasource.getUserEventsList().collect {
                emit(it)
            }
        }
    }
}