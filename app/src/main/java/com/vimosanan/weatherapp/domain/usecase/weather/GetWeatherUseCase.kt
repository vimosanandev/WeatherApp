package com.vimosanan.weatherapp.domain.usecase.weather

import com.vimosanan.weatherapp.core.di.IODispatcher
import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.domain.repository.WeatherRepository
import com.vimosanan.weatherapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class GetWeatherParams(
    val lat: Double,
    val lon: Double,
)

class GetWeatherUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository,
) : BaseUseCase<GetWeatherParams, OperationResult<Weather>>(dispatcher) {
    override suspend fun execute(params: GetWeatherParams): OperationResult<Weather> =
        weatherRepository.getWeather(params.lat, params.lon)
}
