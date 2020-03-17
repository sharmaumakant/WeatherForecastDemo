package com.weather.forecast.model.network

import com.weather.forecast.model.data.WeatherForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Ths interface is used in retrofit api call and resolved by that library only
 */
interface WeatherForecastService {

    /**
     * GET api call
     */
    @GET("weather")
    fun getWeatherForecast(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("appid") appId: String
    ): Call<WeatherForecastResponse>
}