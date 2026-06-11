package com.vimosanan.weatherapp.data.remote.datasource

import com.vimosanan.weatherapp.domain.model.Weather

interface WeatherRemoteDataSource {
    suspend fun getWeather(lat: Double, lon: Double): Weather
}
