package com.vimosanan.weatherapp.core.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureExtensionTest {

    // ── kelvinToFahrenheit ────────────────────────────────────────────────────

    @Test
    fun `kelvinToFahrenheit converts absolute zero correctly`() {
        val result = 0f.kelvinToFahrenheit()
        assertEquals(-459.67f, result, 0.01f)
    }

    @Test
    fun `kelvinToFahrenheit converts 273_15K to 32F`() {
        val result = 273.15f.kelvinToFahrenheit()
        assertEquals(32f, result, 0.01f)
    }

    @Test
    fun `kelvinToFahrenheit converts 373_15K to 212F`() {
        val result = 373.15f.kelvinToFahrenheit()
        assertEquals(212f, result, 0.01f)
    }

    @Test
    fun `kelvinToFahrenheit converts 306_77K correctly`() {
        val result = 306.77f.kelvinToFahrenheit()
        assertEquals(92.516f, result, 0.01f)
    }

    // ── metresPerSecToMph ─────────────────────────────────────────────────────

    @Test
    fun `metresPerSecToMph converts zero`() {
        assertEquals(0f, 0f.metresPerSecToMph(), 0.001f)
    }

    @Test
    fun `metresPerSecToMph converts 1 mps`() {
        assertEquals(2.23694f, 1f.metresPerSecToMph(), 0.001f)
    }

    @Test
    fun `metresPerSecToMph converts 10 mps`() {
        assertEquals(22.3694f, 10f.metresPerSecToMph(), 0.001f)
    }

    @Test
    fun `metresPerSecToMph converts 3_13 mps`() {
        val result = 3.13f.metresPerSecToMph()
        assertEquals(6.9996f, result, 0.01f)
    }
}
