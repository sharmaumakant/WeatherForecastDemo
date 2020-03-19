package com.weather.forecast.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CalendarUtilsTest {

    @Test
    fun canFetchLatestWeather() {
        var actual = CalendarUtils.canFetchLatestWeather(System.currentTimeMillis())
        Assert.assertEquals(actual, false)
    }

}