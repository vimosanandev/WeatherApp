package com.vimosanan.weatherapp.domain.common

sealed class NetworkError {
    data class HttpError(val code: Int, val message: String) : NetworkError()
    data object NoInternet : NetworkError()
    data object Timeout : NetworkError()
    data class Unknown(val throwable: Throwable) : NetworkError()
}