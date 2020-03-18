package com.weather.forecast.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.weather.forecast.R
import com.weather.forecast.model.data.WeatherForecastRepository
import com.weather.forecast.model.location.DeviceLocation
import com.weather.forecast.model.location.FusedLocationServiceProvider
import com.weather.forecast.model.location.ILocationRecieved
import com.weather.forecast.model.network.ApiClient
import com.weather.forecast.model.network.WeatherForecastServiceAccess
import com.weather.forecast.utils.Constants.ACCESS_FINE_LOCATION_REQUEST_CODE
import com.weather.forecast.utils.getWifiManager
import com.weather.forecast.viewmodel.WeatherForecastViewModel
import kotlinx.android.synthetic.main.activity_weather_forecast_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/**
 * Activity class
 */
class WeatherForecastActivity : AppCompatActivity(), ILocationRecieved {

    private lateinit var weatherForecastViewModel: WeatherForecastViewModel
    private lateinit var fusedLocationServiceProvider: FusedLocationServiceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast_layout)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                ACCESS_FINE_LOCATION_REQUEST_CODE
            )
        } else {
            initialize()
        }
    }

    /**
     * android activity lifecycle method :: onResume
     */
    override fun onResume() {
        super.onResume()
        if (::fusedLocationServiceProvider.isInitialized)
            fusedLocationServiceProvider.connect()
    }

    /**
     * android activity lifecycle method :: onPause
     */
    override fun onPause() {
        super.onPause()
        if (::fusedLocationServiceProvider.isInitialized)
            fusedLocationServiceProvider.disconnect()
    }

    /**
     * android method
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == -1)
            return
        if (requestCode == ACCESS_FINE_LOCATION_REQUEST_CODE)
            initialize()
    }

    /**
     * used for initialization process
     */
    private fun initialize() {
        val weatherForecastRepository = WeatherForecastRepository(application)
        val weatherForecastServiceAccess =
            WeatherForecastServiceAccess(ApiClient.apiClient, weatherForecastRepository)
        weatherForecastViewModel = WeatherForecastViewModel.Factory(
                application,
                weatherForecastRepository,
                weatherForecastServiceAccess
            )
            .create(WeatherForecastViewModel::class.java)
        fusedLocationServiceProvider = FusedLocationServiceProvider(this, this)
        fusedLocationServiceProvider.configure()
    }

    /**
     * @param deviceLocation
     */
    override fun sendDeviceCurrentLocation(deviceLocation: DeviceLocation) {
        fetchWeatherForecast(deviceLocation)
    }

    /**
     * @param deviceLocation
     */
    private fun fetchWeatherForecast(deviceLocation: DeviceLocation) {
        if (getWifiManager().isWifiEnabled) {
            CoroutineScope(IO).launch {
                weatherForecastViewModel.fetchWeatherForecast(deviceLocation)
                updateUI()
            }
        } else {
            Toast.makeText(
                this@WeatherForecastActivity,
                R.string.please_check_your_wi_fi,
                Toast.LENGTH_SHORT
            ).show()
            updateUI()
        }
    }

    /**
     * update ui
     */
    private fun updateUI() {
        // update ui on main thread
        CoroutineScope(Main).launch {
            weatherForecastViewModel.getWeatherForecast()
                .observe(this@WeatherForecastActivity, Observer {
                    currentLocation.text = it?.locationName
                    weatherType.text = it?.weatherType
                    weatherDescription.text = it?.description
                    feelsLikeTemp.text = it?.feelsLike.toString()
                    windSpeed.text = it?.windSpeed.toString()
                })
        }
    }
}