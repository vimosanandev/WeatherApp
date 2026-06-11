package com.vimosanan.weatherapp.presentation.feature.home.viewmodel

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vimosanan.weatherapp.domain.common.OperationResult
import com.vimosanan.weatherapp.domain.usecase.location.GetCurrentLocationUseCase
import com.vimosanan.weatherapp.domain.usecase.weather.GetWeatherParams
import com.vimosanan.weatherapp.domain.usecase.weather.GetWeatherUseCase
import com.vimosanan.weatherapp.presentation.feature.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        if (getCurrentLocationUseCase.isPermissionGranted()) {
            fetchWeatherFromLocation()
        }
    }

    fun onLocationPermissionResult(granted: Boolean) {
        if (granted) {
            _uiState.update { it.copy(locationPermissionDenied = false) }
            fetchWeatherFromLocation()
        } else {
            _uiState.update { it.copy(locationPermissionDenied = true) }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onSearchSubmit() {
        val query = _uiState.value.searchQuery.trim()
        if (query.isEmpty()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val location = withContext(Dispatchers.IO) {
                runCatching { geocode(query) }.getOrNull()
            }

            if (location != null) {
                fetchWeather(lat = location.latitude, lon = location.longitude)
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Location \"$query\" not found") }
            }
        }
    }

    private fun fetchWeatherFromLocation() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val location = getCurrentLocationUseCase()
            if (location != null) {
                fetchWeather(lat = location.latitude, lon = location.longitude)
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
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

    private suspend fun geocode(query: String): Address? {
        val geocoder = Geocoder(getApplication())
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCancellableCoroutine { cont ->
                geocoder.getFromLocationName(query, 1) { results ->
                    cont.resume(results.firstOrNull())
                }
            }
        } else {
            @Suppress("DEPRECATION")
            geocoder.getFromLocationName(query, 1)?.firstOrNull()
        }
    }
}
