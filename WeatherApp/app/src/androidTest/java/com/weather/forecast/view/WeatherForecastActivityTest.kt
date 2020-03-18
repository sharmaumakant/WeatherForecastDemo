package com.weather.forecast.view

import android.content.pm.ActivityInfo
import android.view.View
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.weather.forecast.R
import com.weather.forecast.model.location.DeviceLocation
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherForecastActivityTest {

    @get:Rule val activityTestRule: ActivityTestRule<WeatherForecastActivity> =
        ActivityTestRule(WeatherForecastActivity::class.java)

    private var weatherForecastActivity: WeatherForecastActivity?  = null

    @Before
    fun setup(){
        weatherForecastActivity = activityTestRule.activity
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.weather.forecast", appContext.packageName)
    }

    @Test
    fun onCreate() {
        val weatherDescriptionId: View? = weatherForecastActivity?.findViewById(R.id.weatherDescription)
        assertThat(weatherDescriptionId, notNullValue())
        val feelsLikeTempId: View? = weatherForecastActivity?.findViewById(R.id.feelsLikeTemp)
        assertThat(feelsLikeTempId, notNullValue())
        val currentLocationId: View? = weatherForecastActivity?.findViewById(R.id.currentLocation)
        assertThat(currentLocationId, notNullValue())
        val weatherTypeId: View? = weatherForecastActivity?.findViewById(R.id.weatherType)
        assertThat(weatherTypeId, notNullValue())
        val windSpeedId: View? = weatherForecastActivity?.findViewById(R.id.windSpeed)
        assertThat(windSpeedId, notNullValue())
    }

    @Test
    fun onResume() {
        val weatherDescriptionId: TextView? = weatherForecastActivity?.findViewById(R.id.weatherDescription)
        assertThat(weatherDescriptionId, notNullValue())
        weatherDescriptionId?.text = "Wind is cool"
    }

    @Test
    fun onPause() {
        weatherForecastActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    @Test
    fun onRequestPermissionsResult() {
    }

    @Test
    fun sendDeviceCurrentLocation() {
        weatherForecastActivity?.sendDeviceCurrentLocation(DeviceLocation(18.9, 78.8))
    }

    @After
    fun teardown(){
        weatherForecastActivity = null
    }
}