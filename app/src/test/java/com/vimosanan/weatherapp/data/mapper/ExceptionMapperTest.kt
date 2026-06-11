package com.vimosanan.weatherapp.data.mapper

import com.vimosanan.weatherapp.domain.common.NetworkError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ExceptionMapperTest {

    @Test
    fun `HttpException maps to HttpError with code`() {
        val exception = mock<HttpException>()
        whenever(exception.code()).thenReturn(404)
        whenever(exception.response()).thenReturn(null)
        whenever(exception.message()).thenReturn("Not Found")

        val result = ExceptionMapper.toNetworkError(exception)

        assertTrue(result is NetworkError.HttpError)
        assertEquals(404, (result as NetworkError.HttpError).code)
    }

    @Test
    fun `SocketTimeoutException maps to Timeout`() {
        val result = ExceptionMapper.toNetworkError(SocketTimeoutException())
        assertEquals(NetworkError.Timeout, result)
    }

    @Test
    fun `IOException maps to NoInternet`() {
        val result = ExceptionMapper.toNetworkError(IOException())
        assertEquals(NetworkError.NoInternet, result)
    }

    @Test
    fun `Unknown exception maps to Unknown`() {
        val throwable = RuntimeException("unexpected")
        val result = ExceptionMapper.toNetworkError(throwable)

        assertTrue(result is NetworkError.Unknown)
        assertEquals(throwable, (result as NetworkError.Unknown).throwable)
    }

    @Test
    fun `HttpException 401 maps to HttpError with code 401`() {
        val exception = mock<HttpException>()
        whenever(exception.code()).thenReturn(401)
        whenever(exception.response()).thenReturn(null)
        whenever(exception.message()).thenReturn("Unauthorized")

        val result = ExceptionMapper.toNetworkError(exception)

        assertTrue(result is NetworkError.HttpError)
        assertEquals(401, (result as NetworkError.HttpError).code)
    }

    @Test
    fun `HttpException 500 maps to HttpError with code 500`() {
        val exception = mock<HttpException>()
        whenever(exception.code()).thenReturn(500)
        whenever(exception.response()).thenReturn(null)
        whenever(exception.message()).thenReturn("Internal Server Error")

        val result = ExceptionMapper.toNetworkError(exception)

        assertEquals(500, (result as NetworkError.HttpError).code)
    }
}
