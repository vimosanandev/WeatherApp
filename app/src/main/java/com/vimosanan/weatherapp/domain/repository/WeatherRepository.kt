package com.vimosanan.weatherapp.domain.repository

import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): OperationResult<Weather>
}
