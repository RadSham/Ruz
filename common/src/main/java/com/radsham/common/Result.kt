package com.radsham.common

sealed interface Result<T> {
    data class Loading<T>(val message: String) : Result<T>
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val exception: Throwable) : Result<T>
}