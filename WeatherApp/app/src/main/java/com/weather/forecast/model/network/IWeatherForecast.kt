package com.weather.forecast.model.network

interface IWeatherForecast {
    fun fetchWeatherForecast(latitude: Double?, longitude: Double?, appId: String)
}