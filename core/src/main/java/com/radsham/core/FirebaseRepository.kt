package com.radsham.core

import com.radsham.network.FirebaseDatasource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val datasource: FirebaseDatasource
) {

    fun listenForEvents() = flow {
        while (true) {
            val latestNews = datasource.getEventsList()
            emit(latestNews)
            delay(5000)
        }
    }
}