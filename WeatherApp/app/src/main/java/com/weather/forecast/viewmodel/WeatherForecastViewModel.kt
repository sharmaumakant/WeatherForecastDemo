package com.weather.forecast.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weather.forecast.model.data.WeatherForecast
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.location.DeviceLocation
import com.weather.forecast.model.network.WeatherForecastServiceAccess
import com.weather.forecast.utils.Constants.App_ID

class WeatherForecastViewModel(
    application: Application,
    private val weatherForecastRepository: WeatherForecastRepository,
    private val weatherForecastServiceAccess: WeatherForecastServiceAccess
) : AndroidViewModel(application) {

    /* Factory for creating FeatureViewModel instances */
    class Factory(
        private val application: Application,
        private val repository: WeatherForecastRepository,
        private val weatherForecastServiceAccess: WeatherForecastServiceAccess
    ) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WeatherForecastViewModel(
                application,
                repository,
                weatherForecastServiceAccess
            ) as T
        }
    }

    suspend fun getWeatherForecast(): LiveData<WeatherForecast> {
        return weatherForecastRepository.getWeatherForecast()
    }

    fun fetchWeatherForecast(deviceLocation: DeviceLocation) {
        weatherForecastServiceAccess.fetchWeatherForecast(
            deviceLocation.latitude,
            deviceLocation.longitude, App_ID
        )
    }
}