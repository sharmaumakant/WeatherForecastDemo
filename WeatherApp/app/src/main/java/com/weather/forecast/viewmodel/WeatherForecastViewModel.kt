package com.weather.forecast.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.weather.forecast.model.data.WeatherForecast
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.location.DeviceLocation
import com.weather.forecast.model.network.WeatherForecastServiceAccess
import com.weather.forecast.model.network.WeatherForecastWorker
import com.weather.forecast.utils.CalendarUtils
import com.weather.forecast.utils.Constants.App_ID
import com.weather.forecast.utils.Constants.REPEAT_INTERVAL
import com.weather.forecast.utils.Constants.TIME_STAMP_KEY
import com.weather.forecast.utils.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Viewmodel class
 */
class WeatherForecastViewModel(
    application: Application,
    private val weatherForecastRepository: WeatherForecastRepository,
    private val weatherForecastServiceAccess: WeatherForecastServiceAccess
) : AndroidViewModel(application) {

    private lateinit var deviceLocation: DeviceLocation
    private val workManager: WorkManager = WorkManager.getInstance(application)
    /**
     * Factory for creating FeatureViewModel instances
     */
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

    /**
     * accessing weather forecast information using repository
     */
    suspend fun getWeatherForecast(): LiveData<WeatherForecast> {
        return weatherForecastRepository.getWeatherForecast()
    }

    /**
     * accessing weather forecast using api call
     * @param deviceLocation
     */
    fun fetchWeatherForecast(deviceLocation: DeviceLocation) {
        this.deviceLocation = deviceLocation
        CoroutineScope(IO).launch {
            if (CalendarUtils.canFetchLatestWeather(
                    Preferences(getApplication()).getLastTimeStamp(
                        TIME_STAMP_KEY))) {
                weatherForecastServiceAccess.fetchWeatherForecast(
                    deviceLocation.latitude,
                    deviceLocation.longitude, App_ID)
                Preferences(getApplication()).setLastTimeStamp(TIME_STAMP_KEY)
            }
            scheduleWorkManagerForFetchingWeather()
        }
    }

    /**
     * starts periodic api request using work manager
     */
    private fun scheduleWorkManagerForFetchingWeather() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val request = PeriodicWorkRequestBuilder<WeatherForecastWorker>(
            REPEAT_INTERVAL,
            TimeUnit.HOURS
        ).setConstraints(constraints).build()
        workManager.enqueue(request)
    }
}