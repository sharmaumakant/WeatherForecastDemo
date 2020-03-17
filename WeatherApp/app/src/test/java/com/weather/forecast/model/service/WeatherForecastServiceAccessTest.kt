package com.weather.forecast.model.service

import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.network.ApiClient
import com.weather.forecast.model.network.WeatherForecastServiceAccess
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class WeatherForecastServiceAccessTest {

    @Rule @JvmField var mockitoRule: MockitoRule? = MockitoJUnit.rule()

    @Mock lateinit var weatherForecastRepository: WeatherForecastRepository

    lateinit var weatherForecastServiceAccess: WeatherForecastServiceAccess

    @Before
    fun setup() {
        weatherForecastServiceAccess =
            WeatherForecastServiceAccess(ApiClient.apiClient, weatherForecastRepository)
    }

    @Test
    fun fetchWeatherForecast() {
        val latitude: Double? = 139.01
        val longitude: Double? = 35.02
        val appId = "5ad7218f2e11df834b0eaf3a33a39d2a"

        weatherForecastServiceAccess.fetchWeatherForecast(latitude, longitude, appId)
    }
}