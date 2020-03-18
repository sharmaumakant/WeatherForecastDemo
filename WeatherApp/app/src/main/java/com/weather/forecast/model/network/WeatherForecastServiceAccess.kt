package com.weather.forecast.model.network

import com.weather.forecast.model.data.WeatherForecast
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.data.WeatherForecastResponse
import com.weather.forecast.utils.Constants.App_ID
import com.weather.forecast.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * This is a wrapper class used to access weather forecast information using retrofit client library
 */
class WeatherForecastServiceAccess(
    private val apiClient: Retrofit,
    private val weatherForecastRepository: WeatherForecastRepository) : IWeatherForecast {

    /**
     * retrofit api call to fatch weather forecast information
     * @param latitude
     * @param longitude
     * @param appId
     */
    override fun fetchWeatherForecast(latitude: Double?, longitude: Double?, appId: String) {

        val weatherForecastService = apiClient.create(WeatherForecastService::class.java)

        weatherForecastService.getWeatherForecast(
                latitude = latitude,
                longitude = longitude,
                appId = App_ID
            )
            .enqueue(object : Callback<WeatherForecastResponse> {
                /**
                 * on successful api call
                 */
                override fun onResponse(
                    call: Call<WeatherForecastResponse>,
                    response: Response<WeatherForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val weatherForecastResponse = response.body()
                        if (weatherForecastResponse != null) {
                            val weatherForecast = WeatherForecast(
                                ID = 1, locationName = weatherForecastResponse.name,
                                currentTemp = weatherForecastResponse.main.temp,
                                description = weatherForecastResponse.weather[0].description,
                                dt = weatherForecastResponse.dt, feelsLike = weatherForecastResponse.main.feels_like,
                                sunRise = weatherForecastResponse.sys.sunrise,
                                sunSet = weatherForecastResponse.sys.sunset,
                                timezone =  weatherForecastResponse.timezone,
                                weatherType = weatherForecastResponse.weather[0].main,
                                windDeg = weatherForecastResponse.wind.deg,
                                windSpeed = weatherForecastResponse.wind.speed)

                            CoroutineScope(IO).launch {
                                if(weatherForecastRepository.getCount() == 0){
                                    weatherForecastRepository.insert(weatherForecast)
                                }else{
                                    weatherForecastRepository.update(weatherForecast)
                                }
                            }
                        }
                    }
                    Logger.info(
                        WeatherForecastServiceAccess::class.simpleName,
                        "Weather forecast api response ",
                        response.body().toString()
                    )
                }

                /**
                 * on api failure
                 */
                override fun onFailure(call: Call<WeatherForecastResponse>, throwable: Throwable) {
                    Logger.error(
                        WeatherForecastServiceAccess::class.simpleName,
                        "Weather forecast api failure ",
                        throwable.localizedMessage
                    )
                }
            })
    }
}