package com.vimosanan.weatherapp.domain.usecase.city

import com.vimosanan.weatherapp.domain.repository.RecentCityRepository
import javax.inject.Inject

class SaveRecentCityUseCase @Inject constructor(
    private val repository: RecentCityRepository,
) {
    suspend operator fun invoke(lat: Double, lon: Double) = repository.saveRecentCity(lat, lon)
}
