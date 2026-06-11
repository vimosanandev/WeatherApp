package com.vimosanan.weatherapp.domain.usecase.city

import com.vimosanan.weatherapp.domain.repository.RecentCityRepository
import javax.inject.Inject

class GetRecentCityUseCase @Inject constructor(
    private val repository: RecentCityRepository,
) {
    suspend operator fun invoke(): Pair<Double, Double>? {
        val lat = repository.getRecentCityLat()
        val lon = repository.getRecentCityLon()
        return if (lat != null && lon != null) Pair(lat, lon) else null
    }
}
