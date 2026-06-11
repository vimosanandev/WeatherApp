package com.vimosanan.weatherapp.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentCityDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : RecentCityDataStore {

    private object Keys {
        val RECENT_CITY_LAT = stringPreferencesKey("recent_city_lat")
        val RECENT_CITY_LON = stringPreferencesKey("recent_city_lng")
    }

    override suspend fun saveRecentCity(lat: Double, lon: Double) {
        dataStore.edit {
            it[Keys.RECENT_CITY_LAT] = lat.toString()
            it[Keys.RECENT_CITY_LON] = lon.toString()
        }
    }

    override suspend fun getRecentCityLat(): Double? =
        dataStore.data.map { it[Keys.RECENT_CITY_LAT]?.toDoubleOrNull() }.firstOrNull()

    override suspend fun getRecentCityLon(): Double? =
        dataStore.data.map { it[Keys.RECENT_CITY_LON]?.toDoubleOrNull() }.firstOrNull()
}
