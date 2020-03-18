package com.weather.forecast.model.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A repository class used to access database
 * @param application
 */
class WeatherForecastRepository(application: Application) {

    private val weatherForecastDatabase = WeatherForecastDatabase(application)
    private var weatherForecastDao: WeatherForecastDao =
        weatherForecastDatabase.weatherForecastDao()

    /**
     * @param weatherForecast
     */
    fun insert(weatherForecast: WeatherForecast) {
        CoroutineScope(IO).launch {
            weatherForecastDao.insert(weatherForecast)
        }
    }

    /**
     * @param weatherForecast
     */
    fun update(weatherForecast: WeatherForecast) {
        CoroutineScope(IO).launch {
            weatherForecastDao.update(weatherForecast)
        }
    }

    /**
     * suspend function used to fetch weather data
     */
    suspend fun getWeatherForecast(): LiveData<WeatherForecast> {
        val result = CoroutineScope(IO).async {
            getWeatherForecastAsync()
        }
        return result.await()
    }

    /**
     * method is used to fetch weather data stored into weather database
     */
    private fun getWeatherForecastAsync(): LiveData<WeatherForecast> {
        return weatherForecastDao.getWeatherForecast()
    }

    /**
     * method is used to fetch weather data count stored into weather database
     */
    fun getCount(): Int {
        return weatherForecastDao.getCount()
    }
}