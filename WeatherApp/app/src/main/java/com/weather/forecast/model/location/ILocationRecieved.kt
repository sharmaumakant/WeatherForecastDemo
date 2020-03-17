package com.weather.forecast.model.location

interface ILocationRecieved {
    fun sendDeviceCurrentLocation(deviceLocation: DeviceLocation)
}