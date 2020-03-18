package com.weather.forecast.utils

/**
 * constants class
 */
object Constants {
    // api base url
    const val Base_URL = "http://api.openweathermap.org/data/2.5/"

    // weather forecast api key
    const val App_ID = "5ad7218f2e11df834b0eaf3a33a39d2a"

    // fused location provider
    const val CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000

    //access location request code
    const val ACCESS_FINE_LOCATION_REQUEST_CODE = 200

    // time stamp key
    const val TIME_STAMP_KEY = "time_stamp_key"

    // api fetch schedule interval used in work manager
    const val REPEAT_INTERVAL: Long = 2

    // api fetch schedule interval used in calendar
    const val FREQUENCY: Int = 2
}

