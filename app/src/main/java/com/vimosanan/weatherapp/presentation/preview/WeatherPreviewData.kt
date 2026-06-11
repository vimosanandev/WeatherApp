package com.vimosanan.weatherapp.presentation.preview

import com.vimosanan.weatherapp.domain.model.Main
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.domain.model.WeatherCondition
import com.vimosanan.weatherapp.domain.model.Wind

object WeatherPreviewData {
    val weather = Weather(
        city = "Dallas",
        condition = WeatherCondition(
            id = 802,
            main = "Clouds",
            description = "scattered clouds",
            icon = "03d",
        ),
        main = Main(
            temp = 306.77f,
            feelsLike = 313.51f,
            tempMin = 305.66f,
            tempMax = 307.93f,
            humidity = 50
        ),
        wind = Wind(speed = 3.13f),
    )
}
