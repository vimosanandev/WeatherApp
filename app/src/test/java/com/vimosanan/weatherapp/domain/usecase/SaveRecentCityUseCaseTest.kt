package com.vimosanan.weatherapp.domain.usecase

import com.vimosanan.weatherapp.domain.repository.RecentCityRepository
import com.vimosanan.weatherapp.domain.usecase.city.SaveRecentCityUseCase
import com.vimosanan.weatherapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class SaveRecentCityUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: RecentCityRepository
    private lateinit var useCase: SaveRecentCityUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = SaveRecentCityUseCase(repository)
    }

    @Test
    fun `invoke delegates to repository with correct lat and lon`() =
        runTest {
            useCase(lat = 32.94, lon = -96.72)
            verify(repository).saveRecentCity(lat = 32.94, lon = -96.72)
        }

    @Test
    fun `invoke passes different coordinates correctly`() =
        runTest {
            useCase(lat = 51.5, lon = -0.12)
            verify(repository).saveRecentCity(lat = 51.5, lon = -0.12)
        }
}
