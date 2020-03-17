package com.weather.forecast.viewmodel

import android.app.Application
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.location.DeviceLocation
import com.weather.forecast.model.network.WeatherForecastServiceAccess
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule


@RunWith(MockitoJUnitRunner::class)
class WeatherForecastViewModelTest {

    @Rule @JvmField var mockitoRule: MockitoRule? = MockitoJUnit.rule()

    @Mock lateinit var weatherForecastServiceAccess: WeatherForecastServiceAccess

    @Mock lateinit var weatherForecastRepository: WeatherForecastRepository

    @Mock lateinit var application: Application

    @Mock lateinit var deviceLocation: DeviceLocation

    private lateinit var weatherForecastViewModel: WeatherForecastViewModel

    @Before
    fun setup() {
        weatherForecastViewModel = WeatherForecastViewModel(
            application,
            weatherForecastRepository,
            weatherForecastServiceAccess
        )
    }

    @Test
    fun fetchWeatherForecastTest() {
        // arrange the device location data
        deviceLocation.latitude = 139.01
        deviceLocation.longitude = 35.02
        val appId = "5ad7218f2e11df834b0eaf3a33a39d2a"

        // test the method for fetching weather forecast information
        weatherForecastViewModel.fetchWeatherForecast(deviceLocation)

        // verify the call
        verify(weatherForecastServiceAccess).fetchWeatherForecast(
            deviceLocation.latitude,
            deviceLocation.longitude,
            appId
        )
    }
}