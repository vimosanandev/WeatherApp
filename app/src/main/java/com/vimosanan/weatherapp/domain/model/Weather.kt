package com.vimosanan.weatherapp.domain.model

data class Weather(
    val city: String?,
    val wind: Wind?,
    val main: Main?,
    val condition: WeatherCondition?,
)

data class Wind(
    val speed: Float?,
)

data class Main(
    val temp: Float?,
    val feelsLike: Float?,
    val tempMin: Float?,
    val tempMax: Float?,
    val humidity: Int?,
)

data class WeatherCondition(
    val id: Int,
    val main: String?,
    val description: String?,
    val icon: String?,
)
