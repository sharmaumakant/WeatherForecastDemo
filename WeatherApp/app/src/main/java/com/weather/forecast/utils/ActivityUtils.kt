package com.weather.forecast.utils

import android.content.Context
import android.net.wifi.WifiManager

fun Context.getWifiManager(): WifiManager {
    return getSystemService(Context.WIFI_SERVICE) as WifiManager
}