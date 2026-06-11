package com.vimosanan.weatherapp.data.cache

import com.vimosanan.weatherapp.core.datastore.RecentCityDataStore
import com.vimosanan.weatherapp.domain.repository.RecentCityRepository
import javax.inject.Inject

class RecentCityRepositoryImpl @Inject constructor(
    private val dataStore: RecentCityDataStore,
) : RecentCityRepository {

    override suspend fun saveRecentCity(lat: Double, lon: Double) =
        dataStore.saveRecentCity(lat, lon)

    override suspend fun getRecentCityLat(): Double? =
        dataStore.getRecentCityLat()

    override suspend fun getRecentCityLon(): Double? =
        dataStore.getRecentCityLon()
}
