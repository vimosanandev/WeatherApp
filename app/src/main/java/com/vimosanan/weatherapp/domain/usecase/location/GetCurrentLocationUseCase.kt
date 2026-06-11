package com.vimosanan.weatherapp.domain.usecase.location

import android.location.Location
import com.vimosanan.weatherapp.domain.repository.LocationRepository
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
) {
    fun isPermissionGranted(): Boolean = locationRepository.isPermissionGranted()

    suspend operator fun invoke(): Location? = locationRepository.getCurrentLocation()
}
