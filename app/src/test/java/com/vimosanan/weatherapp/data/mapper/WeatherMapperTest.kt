package com.vimosanan.weatherapp.data.mapper

import com.vimosanan.weatherapp.data.remote.dto.MainDto
import com.vimosanan.weatherapp.data.remote.dto.WeatherItemDto
import com.vimosanan.weatherapp.data.remote.dto.WeatherResponseDto
import com.vimosanan.weatherapp.data.remote.dto.WindDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class WeatherMapperTest {

    // ── WeatherResponseDto → Weather ──────────────────────────────────────────

    @Test
    fun `WeatherResponseDto toDomain maps city name`() {
        val dto = weatherResponseDto(name = "Dallas")
        assertEquals("Dallas", dto.toDomain().city)
    }

    @Test
    fun `WeatherResponseDto toDomain maps null city`() {
        val dto = weatherResponseDto(name = null)
        assertNull(dto.toDomain().city)
    }

    @Test
    fun `WeatherResponseDto toDomain maps wind`() {
        val dto = weatherResponseDto(wind = WindDto(speed = 3.5f))
        assertEquals(3.5f, dto.toDomain().wind?.speed)
    }

    @Test
    fun `WeatherResponseDto toDomain null wind`() {
        val dto = weatherResponseDto(wind = null)
        assertNull(dto.toDomain().wind)
    }

    @Test
    fun `WeatherResponseDto toDomain maps main`() {
        val main = MainDto(temp = 306f, feelsLike = 310f, tempMin = 300f, tempMax = 310f, humidity = 50)
        val dto = weatherResponseDto(main = main)
        val result = dto.toDomain().main
        assertEquals(306f, result?.temp)
        assertEquals(310f, result?.feelsLike)
        assertEquals(300f, result?.tempMin)
        assertEquals(310f, result?.tempMax)
        assertEquals(50, result?.humidity)
    }

    @Test
    fun `WeatherResponseDto toDomain picks first weather condition`() {
        val items = listOf(
            WeatherItemDto(id = 800, main = "Clear", description = "clear sky", icon = "01d"),
            WeatherItemDto(id = 801, main = "Clouds", description = "few clouds", icon = "02d"),
        )
        val dto = weatherResponseDto(weather = items)
        assertEquals(800, dto.toDomain().condition?.id)
        assertEquals("Clear", dto.toDomain().condition?.main)
    }

    @Test
    fun `WeatherResponseDto toDomain null weather list`() {
        val dto = weatherResponseDto(weather = null)
        assertNull(dto.toDomain().condition)
    }

    @Test
    fun `WeatherResponseDto toDomain empty weather list`() {
        val dto = weatherResponseDto(weather = emptyList())
        assertNull(dto.toDomain().condition)
    }

    // ── WindDto → Wind ────────────────────────────────────────────────────────

    @Test
    fun `WindDto toDomain maps speed`() {
        assertEquals(5.2f, WindDto(speed = 5.2f).toDomain().speed)
    }

    @Test
    fun `WindDto toDomain null speed`() {
        assertNull(WindDto(speed = null).toDomain().speed)
    }

    // ── MainDto → Main ────────────────────────────────────────────────────────

    @Test
    fun `MainDto toDomain maps all fields`() {
        val dto = MainDto(temp = 300f, feelsLike = 305f, tempMin = 295f, tempMax = 308f, humidity = 60)
        val result = dto.toDomain()
        assertEquals(300f, result.temp)
        assertEquals(305f, result.feelsLike)
        assertEquals(295f, result.tempMin)
        assertEquals(308f, result.tempMax)
        assertEquals(60, result.humidity)
    }

    @Test
    fun `MainDto toDomain maps null fields`() {
        val dto = MainDto(temp = null, feelsLike = null, tempMin = null, tempMax = null, humidity = null)
        val result = dto.toDomain()
        assertNull(result.temp)
        assertNull(result.feelsLike)
        assertNull(result.humidity)
    }

    // ── WeatherItemDto → WeatherCondition ─────────────────────────────────────

    @Test
    fun `WeatherItemDto toDomain maps all fields`() {
        val dto = WeatherItemDto(id = 802, main = "Clouds", description = "scattered clouds", icon = "03d")
        val result = dto.toDomain()
        assertEquals(802, result.id)
        assertEquals("Clouds", result.main)
        assertEquals("scattered clouds", result.description)
        assertEquals("03d", result.icon)
    }

    @Test
    fun `WeatherItemDto toDomain null id defaults to 0`() {
        val dto = WeatherItemDto(id = null, main = null, description = null, icon = null)
        assertEquals(0, dto.toDomain().id)
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    private fun weatherResponseDto(
        name: String? = "Dallas",
        wind: WindDto? = WindDto(speed = 3.0f),
        main: MainDto? = MainDto(306f, 310f, 300f, 312f, 50),
        weather: List<WeatherItemDto>? = listOf(WeatherItemDto(800, "Clear", "clear sky", "01d")),
    ) = WeatherResponseDto(name = name, wind = wind, main = main, weather = weather)
}
