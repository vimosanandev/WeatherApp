package com.vimosanan.weatherapp.data.remote.datasource

import com.vimosanan.weatherapp.data.remote.apiservice.WeatherApiService
import com.vimosanan.weatherapp.data.remote.dto.MainDto
import com.vimosanan.weatherapp.data.remote.dto.WeatherItemDto
import com.vimosanan.weatherapp.data.remote.dto.WeatherResponseDto
import com.vimosanan.weatherapp.data.remote.dto.WindDto
import com.vimosanan.weatherapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class WeatherRemoteDataSourceImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var apiService: WeatherApiService
    private lateinit var dataSource: WeatherRemoteDataSourceImpl

    @Before
    fun setUp() {
        apiService = mock()
        dataSource = WeatherRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `getWeather calls apiService with correct lat and lon`() =
        runTest {
            whenever(apiService.getWeather(lat = any(), lon = any(), apiKey = any())).thenReturn(weatherResponseDto())

            dataSource.getWeather(lat = 32.94, lon = -96.72)

            verify(apiService).getWeather(lat = eq(32.94), lon = eq(-96.72), apiKey = any())
        }

    @Test
    fun `getWeather maps city name from response`() =
        runTest {
            whenever(apiService.getWeather(lat = any(), lon = any(), apiKey = any()))
                .thenReturn(weatherResponseDto(name = "Dallas"))

            val result = dataSource.getWeather(lat = 32.94, lon = -96.72)

            assertEquals("Dallas", result.city)
        }

    @Test
    fun `getWeather maps wind speed from response`() =
        runTest {
            whenever(apiService.getWeather(lat = any(), lon = any(), apiKey = any()))
                .thenReturn(weatherResponseDto(wind = WindDto(speed = 5.5f)))

            val result = dataSource.getWeather(lat = 32.94, lon = -96.72)

            assertEquals(5.5f, result.wind?.speed)
        }

    @Test
    fun `getWeather maps null wind to null`() =
        runTest {
            whenever(apiService.getWeather(lat = any(), lon = any(), apiKey = any()))
                .thenReturn(weatherResponseDto(wind = null))

            val result = dataSource.getWeather(lat = 32.94, lon = -96.72)

            assertNull(result.wind)
        }

    @Test
    fun `getWeather maps temperature from main`() =
        runTest {
            val main = MainDto(temp = 306f, feelsLike = 310f, tempMin = 300f, tempMax = 312f, humidity = 55)
            whenever(apiService.getWeather(lat = any(), lon = any(), apiKey = any()))
                .thenReturn(weatherResponseDto(main = main))

            val result = dataSource.getWeather(lat = 32.94, lon = -96.72)

            assertEquals(306f, result.main?.temp)
            assertEquals(55, result.main?.humidity)
        }

    @Test
    fun `getWeather propagates exception from apiService`() =
        runTest {
            whenever(apiService.getWeather(lat = any(), lon = any(), apiKey = any()))
                .thenThrow(RuntimeException("network error"))

            val exception = runCatching { dataSource.getWeather(lat = 32.94, lon = -96.72) }.exceptionOrNull()

            assert(exception is RuntimeException)
        }

    // ── helpers ───────────────────────────────────────────────────────────────

    private fun weatherResponseDto(
        name: String? = "Dallas",
        wind: WindDto? = WindDto(speed = 3.0f),
        main: MainDto? = MainDto(306f, 310f, 300f, 312f, 50),
        weather: List<WeatherItemDto>? = listOf(WeatherItemDto(800, "Clear", "clear sky", "01d")),
    ) = WeatherResponseDto(name = name, wind = wind, main = main, weather = weather)
}
