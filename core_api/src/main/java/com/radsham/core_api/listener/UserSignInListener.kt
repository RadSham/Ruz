package com.radsham.core_api.listener

interface UserSignInListener {
    fun onSuccess()
    fun onFailure(message: String?)
}