package com.radsham.core_api.listener

import com.radsham.core_api.model.User

interface UserCreateListener {
    fun onSuccess(user: User)
    fun onFailure(message: String?)
}