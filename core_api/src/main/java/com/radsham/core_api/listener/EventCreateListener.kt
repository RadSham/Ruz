package com.radsham.core_api.listener

interface EventCreateListener {
    fun onSuccess()
    fun onFailure(message: String?)
}