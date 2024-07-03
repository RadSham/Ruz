package com.radsham.core_api.listener

interface UserAuthorizedListener {
    fun onIamIn()
    fun onIamOut()
    fun onFailure()
}