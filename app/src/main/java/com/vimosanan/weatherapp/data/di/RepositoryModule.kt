package com.vimosanan.weatherapp.data.di

import com.vimosanan.weatherapp.data.location.LocationRepositoryImpl
import com.vimosanan.weatherapp.data.repository.WeatherRepositoryImpl
import com.vimosanan.weatherapp.domain.repository.LocationRepository
import com.vimosanan.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository
}
