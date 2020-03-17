package com.weather.forecast.model.network

import com.weather.forecast.model.data.WeatherForecast
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.data.WeatherForecastResponse
import com.weather.forecast.utils.App_ID
import com.weather.forecast.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WeatherForecastServiceAccess(
    private val apiClient: Retrofit,
    private val weatherForecastRepository: WeatherForecastRepository) : IWeatherForecast {

    override fun fetchWeatherForecast(latitude: Double?, longitude: Double?, appId: String) {

        val weatherForecastService = apiClient.create(WeatherForecastService::class.java)

        weatherForecastService.getWeatherForecast(
                latitude = latitude,
                longitude = longitude,
                appId = App_ID
            )
            .enqueue(object : Callback<WeatherForecastResponse> {
                override fun onResponse(
                    call: Call<WeatherForecastResponse>,
                    response: Response<WeatherForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val weatherForecastResponse = response.body()
                        if (weatherForecastResponse != null) {
                            val weatherForecast = WeatherForecast(
                                weatherForecastResponse.name,
                                weatherForecastResponse.wind.speed,
                                weatherForecastResponse.wind.deg,
                                weatherForecastResponse.dt,
                                weatherForecastResponse.sys.sunrise,
                                weatherForecastResponse.sys.sunset,
                                weatherForecastResponse.timezone,
                                weatherForecastResponse.main.temp,
                                System.currentTimeMillis(),
                                weatherForecastResponse.weather[0].description,
                                weatherForecastResponse.main.feels_like,
                                weatherForecastResponse.weather[0].main
                            )
                            weatherForecastRepository.insert(weatherForecast)
                        }
                    }
                    Logger.info(
                        WeatherForecastServiceAccess::class.simpleName,
                        "Weather forecast api response ",
                        response.body().toString()
                    )
                }

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