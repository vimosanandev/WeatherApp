package com.vimosanan.weatherapp.core.extension

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WeatherIconExtensionTest {

    @Test
    fun `toWeatherIconUrl builds correct url`() {
        val result = "01d".toWeatherIconUrl()
        assertEquals("https://openweathermap.org/img/wn/01d@2x.png", result)
    }

    @Test
    fun `toWeatherIconUrl contains icon code in url`() {
        val iconCode = "03n"
        assertTrue("03n".toWeatherIconUrl().contains(iconCode))
    }

    @Test
    fun `toWeatherIconUrl always appends @2x`() {
        assertTrue("10d".toWeatherIconUrl().contains("@2x"))
    }

    @Test
    fun `toWeatherIconUrl ends with png`() {
        assertTrue("04d".toWeatherIconUrl().endsWith(".png"))
    }

    @Test
    fun `toWeatherIconUrl uses correct base url`() {
        assertTrue("01d".toWeatherIconUrl().startsWith("https://openweathermap.org/img/wn/"))
    }
}
