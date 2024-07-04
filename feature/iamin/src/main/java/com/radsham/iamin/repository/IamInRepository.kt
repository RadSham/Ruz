package com.radsham.iamin.repository

import com.radsham.core_api.FirebaseDatasource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IamInRepository @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
) {
    fun listenForIamInEvents() = flow {
        while (true) {
            firebaseDatasource.getIamInEvents().collect {
                emit(it)
            }
        }
    }
}