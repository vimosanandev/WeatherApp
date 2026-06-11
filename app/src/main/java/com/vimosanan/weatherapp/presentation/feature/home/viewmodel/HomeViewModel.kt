package com.vimosanan.weatherapp.presentation.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.usecase.weather.GetWeatherParams
import com.vimosanan.weatherapp.domain.usecase.weather.GetWeatherUseCase
import com.vimosanan.weatherapp.presentation.feature.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchWeather()
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onSearchSubmit() {
        // TODO: geocode city name to lat/lon and call fetchWeather
    }

    fun fetchWeather(lat: Double = 32.9483, lon: Double = -96.7299) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getWeatherUseCase(GetWeatherParams(lat, lon))) {
                is OperationResult.Success -> _uiState.update { it.copy(isLoading = false, weather = result.data) }
                is OperationResult.Error -> _uiState.update { it.copy(isLoading = false, error = result.error.toString()) }
            }
        }
    }
}
