package com.weather.forecast.utils

import com.weather.forecast.utils.Constants.FREQUENCY
import java.util.*

object CalendarUtils {

    fun canFetchLatestWeather(lastWeatherTimeStamp: Long): Boolean {
        val lastWeatherTime = Calendar.getInstance().apply {
            timeInMillis = lastWeatherTimeStamp
        }

        val someTimeAfterLastWeatherTime = lastWeatherTime.apply {
            add(Calendar.HOUR, FREQUENCY)
        }

        return Calendar.getInstance().after(someTimeAfterLastWeatherTime)
    }
}