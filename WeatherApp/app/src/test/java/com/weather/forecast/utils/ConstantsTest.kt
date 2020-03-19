package com.weather.forecast.utils

import com.weather.forecast.utils.Constants.ACCESS_FINE_LOCATION_REQUEST_CODE
import com.weather.forecast.utils.Constants.App_ID
import com.weather.forecast.utils.Constants.Base_URL
import com.weather.forecast.utils.Constants.CONNECTION_FAILURE_RESOLUTION_REQUEST
import com.weather.forecast.utils.Constants.FREQUENCY
import com.weather.forecast.utils.Constants.REPEAT_INTERVAL
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ConstantsTest {

    @Test
    fun verifyConstants(){
        assertEquals(Base_URL, "http://api.openweathermap.org/data/2.5/")
        assertEquals(App_ID, "5ad7218f2e11df834b0eaf3a33a39d2a")
        assertEquals(FREQUENCY, 2)
        assertEquals(REPEAT_INTERVAL, 2)
        assertEquals(CONNECTION_FAILURE_RESOLUTION_REQUEST, 9000)
        assertEquals(ACCESS_FINE_LOCATION_REQUEST_CODE, 200)
    }
}