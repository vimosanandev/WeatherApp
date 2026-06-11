package com.vimosanan.weatherapp.presentation.feature.home.event

sealed class HomeUiEvent {
    data class LocationNotFound(val query: String) : HomeUiEvent()
    data object FetchWeatherFailed : HomeUiEvent()
}
