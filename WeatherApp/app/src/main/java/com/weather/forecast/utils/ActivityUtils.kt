package com.weather.forecast.utils

import android.content.Context
import android.net.wifi.WifiManager

/**
 * extension method for Context class
 */
fun Context.getWifiManager(): WifiManager {
    return getSystemService(Context.WIFI_SERVICE) as WifiManager
}
