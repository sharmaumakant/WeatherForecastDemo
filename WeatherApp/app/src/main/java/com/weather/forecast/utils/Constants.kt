package com.weather.forecast.utils

object Constants {
    // api base url
    const val Base_URL = "http://api.openweathermap.org/data/2.5/"

    // weather forecast api key
    const val App_ID = "5ad7218f2e11df834b0eaf3a33a39d2a"

    // api fetch schedule interval
    const val Frequency: Long = 2 * 60 * 60 * 1000

    // fused location provider
    const val CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000

    //access location request code
    const val ACCESS_FINE_LOCATION_REQUEST_CODE = 200
}

