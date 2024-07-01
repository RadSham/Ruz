package com.radsham.signin.repository

import com.radsham.core_api.FirebaseDatasource
import com.radsham.core_api.listener.UserSignInListener
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    suspend fun signInUser(
        email: String,
        password: String,
        userSignInListener: UserSignInListener
    ) {
        firebaseDatasource.signInUser(email, password, userSignInListener)
    }
}