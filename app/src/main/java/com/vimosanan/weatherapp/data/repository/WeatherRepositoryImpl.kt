package com.vimosanan.weatherapp.data.repository

import com.vimosanan.weatherapp.data.mapper.ExceptionMapper
import com.vimosanan.weatherapp.data.remote.datasource.WeatherRemoteDataSource
import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): OperationResult<Weather> =
        runCatching { remoteDataSource.getWeather(lat, lon) }
            .fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(ExceptionMapper.toNetworkError(it)) },
            )
}
