package com.vimosanan.weatherapp.core.extension

fun String.toWeatherIconUrl(): String =
    "https://openweathermap.org/img/wn/${this}@2x.png"
