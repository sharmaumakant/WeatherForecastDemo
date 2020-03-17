package com.weather.forecast.model.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class WeatherForecastRepository(application: Application) {

    private val weatherForecastDatabase = WeatherForecastDatabase(application)
    private var weatherForecastDao: WeatherForecastDao =
        weatherForecastDatabase.weatherForecastDao()

    fun insert(weatherForecast: WeatherForecast) {
        CoroutineScope(IO).launch {
            weatherForecastDao.insert(weatherForecast)
        }
    }

    // for future use for updating database call
    fun update(weatherForecast: WeatherForecast) {
        CoroutineScope(IO).launch {
            weatherForecastDao.update(weatherForecast)
        }
    }

    suspend fun getWeatherForecast(): LiveData<WeatherForecast> {
        val result = CoroutineScope(IO).async {
            getWeatherForecastAsync()
        }
        return result.await()
    }

    private fun getWeatherForecastAsync(): LiveData<WeatherForecast> {
        return weatherForecastDao.getWeatherForecast()
    }
}