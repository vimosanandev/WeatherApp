package com.vimosanan.weatherapp.data.di

import com.vimosanan.weatherapp.data.remote.datasource.WeatherRemoteDataSource
import com.vimosanan.weatherapp.data.remote.datasource.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRemoteDataSource(impl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource
}
