package com.vimosanan.weatherapp.domain.repository

interface RecentCityRepository {
    suspend fun saveRecentCity(lat: Double, lon: Double)
    suspend fun getRecentCityLat(): Double?
    suspend fun getRecentCityLon(): Double?
}
