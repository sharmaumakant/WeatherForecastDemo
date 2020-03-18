package com.weather.forecast.model.network


import android.app.Application
import android.content.Context
import android.location.Location
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.utils.CalendarUtils
import com.weather.forecast.utils.Constants.App_ID
import com.weather.forecast.utils.Constants.TIME_STAMP_KEY
import com.weather.forecast.utils.Logger
import com.weather.forecast.utils.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/***
 * WeatherForecastWorker
 * @param context
 * @param workerParams
 */
class WeatherForecastWorker(private val context: Context,
                            workerParams: WorkerParameters) : Worker(context, workerParams){

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val weatherForecastServiceAccess =
        WeatherForecastServiceAccess(ApiClient.apiClient, WeatherForecastRepository(context as Application))

    override fun doWork(): Result {
        Logger.info(WeatherForecastWorker::class.simpleName, "doWork(): ", "start fetching weather forecast")
        fetchLastKnownLocation()
        return Result.success()
    }

    private fun fetchLastKnownLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                CoroutineScope(IO).launch {
                    if (CalendarUtils.canFetchLatestWeather(Preferences(context).getLastTimeStamp(TIME_STAMP_KEY))) {
                        weatherForecastServiceAccess.fetchWeatherForecast(it.latitude, it.longitude, App_ID)
                        Preferences(context).setLastTimeStamp(TIME_STAMP_KEY)
                    }
                }
            }
        }
    }
}