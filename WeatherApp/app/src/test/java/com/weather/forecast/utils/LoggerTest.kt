package com.weather.forecast.utils

import android.util.Log
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.log


@RunWith(MockitoJUnitRunner::class)
class LoggerTest {

    @Test
    fun error() {
        Logger.error("tag", "message", "data")
    }

    @Test
    fun info() {
        Logger.info("tag", "message", "data")
    }

}