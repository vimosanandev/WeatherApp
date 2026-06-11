package com.vimosanan.weatherapp.domain.repository

import android.location.Location

interface LocationRepository {
    fun isPermissionGranted(): Boolean
    suspend fun getCurrentLocation(): Location?
}
