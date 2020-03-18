package com.weather.forecast.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * WeatherForecast is a data class
 * It is mapped with table named 'weather_forecast_table'
 */
@Entity(tableName = "weather_forecast_table")
data class WeatherForecast(
    @PrimaryKey val ID: Long? = 1,
    var locationName: String?,
    var windSpeed: Double?,
    var windDeg: Int?,
    var dt: Int?,
    var sunRise: Int?,
    var sunSet: Int?,
    var timezone: Int?,
    var currentTemp: Double?,
    var description: String?,
    var feelsLike: Double?,
    var weatherType: String?
)