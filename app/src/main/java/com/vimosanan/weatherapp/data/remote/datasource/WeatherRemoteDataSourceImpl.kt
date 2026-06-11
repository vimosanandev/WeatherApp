package com.vimosanan.weatherapp.data.remote.datasource

import com.vimosanan.weatherapp.BuildConfig
import com.vimosanan.weatherapp.data.mapper.toDomain
import com.vimosanan.weatherapp.data.remote.apiservice.WeatherApiService
import com.vimosanan.weatherapp.domain.model.Weather
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val apiService: WeatherApiService,
) : WeatherRemoteDataSource {
    override suspend fun getWeather(lat: Double, lon: Double): Weather =
        apiService.getWeather(lat, lon, BuildConfig.WEATHER_API_KEY).toDomain()
}
