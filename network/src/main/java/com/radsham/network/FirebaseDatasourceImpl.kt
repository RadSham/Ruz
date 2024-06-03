package com.radsham.network

import javax.inject.Inject

class FirebaseDatasourceImpl @Inject constructor() : FirebaseDatasource {

    //TODO: change to firebase
    override suspend fun getEventsList(): List<String> {
        return listOf("one", "two", "three", "four")
    }
}