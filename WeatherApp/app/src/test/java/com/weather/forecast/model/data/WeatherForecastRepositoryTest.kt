package com.weather.forecast.model.data

import android.app.Application
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherForecastRepositoryTest {

    @Mock lateinit var weatherForecast: WeatherForecast
    @Mock lateinit var application: Application
    lateinit var weatherForecastRepository: WeatherForecastRepository

    @Before
    fun setup(){
        weatherForecastRepository = WeatherForecastRepository(application)
    }

    @Test
    fun insertTest() {
        weatherForecastRepository.insert(weatherForecast)
    }

    @Test
    fun updateTest() {
        weatherForecastRepository.update(weatherForecast)
    }
}