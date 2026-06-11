package com.vimosanan.weatherapp.core.datastore

interface RecentCityDataStore {
    suspend fun saveRecentCity(lat: Double, lon: Double)
    suspend fun getRecentCityLat(): Double?
    suspend fun getRecentCityLon(): Double?
}
