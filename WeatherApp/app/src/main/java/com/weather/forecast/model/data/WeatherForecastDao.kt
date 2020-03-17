package com.weather.forecast.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface WeatherForecastDao {

    @Insert
    fun insert(weatherForecast: WeatherForecast)

    @Update
    fun update(weatherForecast: WeatherForecast)

    @Query("SELECT * FROM weather_forecast_table")
    fun getWeatherForecast(): LiveData<WeatherForecast>

    @Query("SELECT COUNT(locationName) FROM weather_forecast_table")
    fun getCount(): Int
}