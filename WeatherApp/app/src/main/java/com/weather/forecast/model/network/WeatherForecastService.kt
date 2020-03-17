package com.weather.forecast.model.network

import com.weather.forecast.model.data.WeatherForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherForecastService {

    @GET("weather")
    fun getWeatherForecast(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("appid") appId: String
    ): Call<WeatherForecastResponse>
}