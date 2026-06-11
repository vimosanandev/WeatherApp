package com.vimosanan.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("name") val name: String?,
    @SerializedName("wind") val wind: WindDto?,
    @SerializedName("main") val main: MainDto?,
    @SerializedName("weather") val weather: List<WeatherItemDto>?,
)

data class WindDto(
    @SerializedName("speed") val speed: Float?,
)

data class MainDto(
    @SerializedName("temp") val temp: Float?,
    @SerializedName("feels_like") val feelsLike: Float?,
    @SerializedName("temp_min") val tempMin: Float?,
    @SerializedName("temp_max") val tempMax: Float?,
    @SerializedName("humidity") val humidity: Int?,
)

data class WeatherItemDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("main") val main: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: String?,
)
