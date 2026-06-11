package com.vimosanan.weatherapp.domain.common

sealed class OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Error(val error: NetworkError) : OperationResult<Nothing>()
}
