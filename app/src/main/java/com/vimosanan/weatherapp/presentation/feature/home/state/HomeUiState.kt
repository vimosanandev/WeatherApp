package com.vimosanan.weatherapp.presentation.feature.home.state

import com.vimosanan.weatherapp.domain.model.Weather

data class HomeUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val searchQuery: String = "",
    val locationPermissionDenied: Boolean = false,
)
