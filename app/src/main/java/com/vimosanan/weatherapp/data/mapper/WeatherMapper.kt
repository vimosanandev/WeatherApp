package com.vimosanan.weatherapp.data.mapper

import com.vimosanan.weatherapp.data.remote.dto.MainDto
import com.vimosanan.weatherapp.data.remote.dto.WeatherItemDto
import com.vimosanan.weatherapp.data.remote.dto.WeatherResponseDto
import com.vimosanan.weatherapp.data.remote.dto.WindDto
import com.vimosanan.weatherapp.domain.model.Main
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.domain.model.WeatherCondition
import com.vimosanan.weatherapp.domain.model.Wind

fun WeatherResponseDto.toDomain(): Weather = Weather(
    city = name,
    wind = wind?.toDomain(),
    main = main?.toDomain(),
    condition = weather?.firstOrNull()?.toDomain()
)

fun WindDto.toDomain(): Wind = Wind(speed = speed)

fun MainDto.toDomain(): Main = Main(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    humidity = humidity
)

fun WeatherItemDto.toDomain(): WeatherCondition = WeatherCondition(
    id = id ?: 0,
    main = main,
    description = description,
    icon = icon,
)
