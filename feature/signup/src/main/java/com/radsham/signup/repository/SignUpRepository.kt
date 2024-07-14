package com.radsham.signup.repository

import com.radsham.core_api.listener.UserCreateListener
import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.model.User
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    suspend fun createNewUser(
        user: User,
        password: String,
        userCreateListener: UserCreateListener
    ) {
        firebaseDatasource.createNewUser(user, password, userCreateListener)
    }
}