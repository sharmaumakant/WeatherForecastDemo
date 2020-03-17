package com.weather.forecast.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * It is an interface used by room library
 * It is used for data access layer.
 * In our case used to database operation like insert, delete, update for table 'weather_forecast_table'
 */
@Dao
interface WeatherForecastDao {

    /**
     * For table insert
     */
    @Insert
    fun insert(weatherForecast: WeatherForecast)

    /**
     * For table update
     */
    @Update
    fun update(weatherForecast: WeatherForecast)

    /**
     * For getting data from database
     */
    @Query("SELECT * FROM weather_forecast_table")
    fun getWeatherForecast(): LiveData<WeatherForecast>

    /**
     * For getting item counts
     */
    @Query("SELECT COUNT(locationName) FROM weather_forecast_table")
    fun getCount(): Int
}