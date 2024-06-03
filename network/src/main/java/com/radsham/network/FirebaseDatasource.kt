package com.radsham.network

interface FirebaseDatasource {
    suspend fun getEventsList(): List<String>
}

