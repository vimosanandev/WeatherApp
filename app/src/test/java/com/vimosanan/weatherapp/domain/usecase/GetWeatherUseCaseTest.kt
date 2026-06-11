package com.vimosanan.weatherapp.domain.usecase

import com.vimosanan.weatherapp.domain.common.NetworkError
import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.domain.repository.WeatherRepository
import com.vimosanan.weatherapp.domain.usecase.weather.GetWeatherParams
import com.vimosanan.weatherapp.domain.usecase.weather.GetWeatherUseCase
import com.vimosanan.weatherapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetWeatherUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetWeatherUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetWeatherUseCase(
            dispatcher = mainDispatcherRule.testDispatcher,
            weatherRepository = repository,
        )
    }

    @Test
    fun `invoke delegates to repository with correct params`() =
        runTest {
            val weather = weather()
            whenever(repository.getWeather(lat = 32.94, lon = -96.72)).thenReturn(OperationResult.Success(weather))

            useCase(GetWeatherParams(lat = 32.94, lon = -96.72))

            verify(repository).getWeather(lat = 32.94, lon = -96.72)
        }

    @Test
    fun `invoke returns Success when repository succeeds`() =
        runTest {
            val weather = weather(city = "Dallas")
            whenever(repository.getWeather(lat = any(), lon = any())).thenReturn(OperationResult.Success(weather))

            val result = useCase(GetWeatherParams(lat = 32.94, lon = -96.72))

            assertTrue(result is OperationResult.Success)
            assertEquals("Dallas", (result as OperationResult.Success).data.city)
        }

    @Test
    fun `invoke returns Error when repository returns error`() =
        runTest {
            whenever(repository.getWeather(lat = any(), lon = any()))
                .thenReturn(OperationResult.Error(NetworkError.NoInternet))

            val result = useCase(GetWeatherParams(lat = 32.94, lon = -96.72))

            assertTrue(result is OperationResult.Error)
            assertEquals(NetworkError.NoInternet, (result as OperationResult.Error).error)
        }

    @Test
    fun `invoke returns Timeout error`() =
        runTest {
            whenever(repository.getWeather(lat = any(), lon = any()))
                .thenReturn(OperationResult.Error(NetworkError.Timeout))

            val result = useCase(GetWeatherParams(lat = 0.0, lon = 0.0))

            assertEquals(NetworkError.Timeout, (result as OperationResult.Error).error)
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
