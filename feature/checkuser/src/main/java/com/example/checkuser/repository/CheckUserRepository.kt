package com.example.checkuser.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.User
import javax.inject.Inject

class CheckUserRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {

    suspend fun getCurrentUser(): User {
        return firebaseDatasource.getCurrentUser()
    }
}