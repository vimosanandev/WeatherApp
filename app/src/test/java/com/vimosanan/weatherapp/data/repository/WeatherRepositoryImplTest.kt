package com.vimosanan.weatherapp.data.repository

import com.vimosanan.weatherapp.data.remote.datasource.WeatherRemoteDataSource
import com.vimosanan.weatherapp.domain.common.NetworkError
import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var remoteDataSource: WeatherRemoteDataSource
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mock()
        repository = WeatherRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getWeather success returns OperationResult Success`() =
        runTest {
            val weather = weather(city = "Dallas")
            whenever(remoteDataSource.getWeather(lat = 32.94, lon = -96.72)).thenReturn(weather)

            val result = repository.getWeather(lat = 32.94, lon = -96.72)

            assertTrue(result is OperationResult.Success)
            assertEquals("Dallas", (result as OperationResult.Success).data.city)
        }

    @Test
    fun `getWeather IOException returns NoInternet error`() =
        runTest {
            whenever(remoteDataSource.getWeather(lat = any(), lon = any())).thenAnswer { throw IOException() }

            val result = repository.getWeather(lat = 32.94, lon = -96.72)

            assertTrue(result is OperationResult.Error)
            assertEquals(NetworkError.NoInternet, (result as OperationResult.Error).error)
        }

    @Test
    fun `getWeather SocketTimeoutException returns Timeout error`() =
        runTest {
            whenever(remoteDataSource.getWeather(lat = any(), lon = any())).thenAnswer { throw SocketTimeoutException() }

            val result = repository.getWeather(lat = 32.94, lon = -96.72)

            assertTrue(result is OperationResult.Error)
            assertEquals(NetworkError.Timeout, (result as OperationResult.Error).error)
        }

    @Test
    fun `getWeather unknown exception returns Unknown error`() =
        runTest {
            whenever(remoteDataSource.getWeather(lat = any(), lon = any())).thenAnswer { throw RuntimeException("unexpected") }

            val result = repository.getWeather(lat = 32.94, lon = -96.72)

            assertTrue(result is OperationResult.Error)
            assertTrue((result as OperationResult.Error).error is NetworkError.Unknown)
        }

    @Test
    fun `getWeather success passes weather data through correctly`() =
        runTest {
            val weather = weather(city = "London")
            whenever(remoteDataSource.getWeather(lat = any(), lon = any())).thenReturn(weather)

            val result = repository.getWeather(lat = 51.5, lon = -0.12)

            assertEquals("London", (result as OperationResult.Success).data.city)
        }

    // ── helpers ───────────────────────────────────────────────────────────────

    private fun any(): Double = org.mockito.kotlin.any()

    private fun weather(city: String? = "Dallas") = Weather(
        city = city,
        wind = null,
        main = null,
        condition = null,
    )
}
