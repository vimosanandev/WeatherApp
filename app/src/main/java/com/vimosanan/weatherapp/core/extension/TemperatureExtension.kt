package com.vimosanan.weatherapp.core.extension

fun Float.kelvinToFahrenheit(): Float = (this - 273.15f) * 9f / 5f + 32f

fun Float.metresPerSecToMph(): Float = this * 2.23694f
