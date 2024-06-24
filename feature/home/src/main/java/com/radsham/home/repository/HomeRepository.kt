package com.radsham.home.repository

import com.radsham.core_api.FirebaseDatasource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {

    fun listenForEvents() = flow {
        while (true) {
            firebaseDatasource.getAllEvents().collect {
                emit(it)
            }
        }
    }
}