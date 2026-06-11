package com.vimosanan.weatherapp.data.mapper

import com.vimosanan.weatherapp.domain.common.NetworkError
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ExceptionMapper {
    fun toNetworkError(throwable: Throwable): NetworkError =
        when (throwable) {
            is HttpException -> NetworkError.HttpError(throwable.code(), throwable.parseErrorMessage())
            is SocketTimeoutException -> NetworkError.Timeout
            is IOException -> NetworkError.NoInternet
            else -> NetworkError.Unknown(throwable)
        }

    private fun HttpException.parseErrorMessage(): String =
        try {
            val body = response()?.errorBody()?.string()
            JSONObject(body ?: "")
                .optString("message")
                .takeIf { it.isNotBlank() }
                ?: message()
        } catch (e: Exception) {
            message()
        }
}
