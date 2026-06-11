package com.vimosanan.weatherapp.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.vimosanan.weatherapp.core.datastore.RecentCityDataStore
import com.vimosanan.weatherapp.core.datastore.RecentCityDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("weather_prefs")
        }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreBindingModule {
    @Binds
    @Singleton
    abstract fun bindRecentCityDataStore(impl: RecentCityDataStoreImpl): RecentCityDataStore
}
