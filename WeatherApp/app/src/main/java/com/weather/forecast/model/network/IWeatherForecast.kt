package com.weather.forecast.model.network

/**
 * This interface is used for fetching weather forecast using api call
 */
interface IWeatherForecast {
    /**
     * Ths method is used for fetching weather forecast using api call
     * @param latitude
     * @param longitude
     * @param appId
     */
    fun fetchWeatherForecast(latitude: Double?, longitude: Double?, appId: String)
}