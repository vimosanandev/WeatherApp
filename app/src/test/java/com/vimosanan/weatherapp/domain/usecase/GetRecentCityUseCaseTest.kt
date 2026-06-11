package com.vimosanan.weatherapp.domain.usecase

import com.vimosanan.weatherapp.domain.repository.RecentCityRepository
import com.vimosanan.weatherapp.domain.usecase.city.GetRecentCityUseCase
import com.vimosanan.weatherapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetRecentCityUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: RecentCityRepository
    private lateinit var useCase: GetRecentCityUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetRecentCityUseCase(repository)
    }

    @Test
    fun `invoke returns Pair when both lat and lon exist`() =
        runTest {
            whenever(repository.getRecentCityLat()).thenReturn(32.94)
            whenever(repository.getRecentCityLon()).thenReturn(-96.72)

            val result = useCase()

            assertEquals(Pair(32.94, -96.72), result)
        }

    @Test
    fun `invoke returns null when lat is null`() =
        runTest {
            whenever(repository.getRecentCityLat()).thenReturn(null)
            whenever(repository.getRecentCityLon()).thenReturn(-96.72)

            assertNull(useCase())
        }

    @Test
    fun `invoke returns null when lon is null`() =
        runTest {
            whenever(repository.getRecentCityLat()).thenReturn(32.94)
            whenever(repository.getRecentCityLon()).thenReturn(null)

            assertNull(useCase())
        }

    @Test
    fun `invoke returns null when both are null`() =
        runTest {
            whenever(repository.getRecentCityLat()).thenReturn(null)
            whenever(repository.getRecentCityLon()).thenReturn(null)

            assertNull(useCase())
        }
}
