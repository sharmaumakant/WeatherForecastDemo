package com.weather.forecast.model.data

import com.google.gson.annotations.SerializedName

/**
 * data class weather forecast response
 * response is parsed into this class fields
 */
data class WeatherForecastResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

/**
 * data class for weather information
 */
data class Clouds(@SerializedName("all") val all: Int)

/**
 * data class for co-ordinate information
 */
data class Coord(@SerializedName("lon") val lon: Double, @SerializedName("lat") val lat: Double)

/**
 * data class for temperature information
 */
data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feels_like: Double,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)

/**
 * data class for Sys
 */
data class Sys(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)

/**
 * data class for Weather information
 */
data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

/**
 * data class for wind information
 */
data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
)