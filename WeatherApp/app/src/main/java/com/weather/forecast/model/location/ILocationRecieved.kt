package com.weather.forecast.model.location

/**
 * This interface is used for sending device current location
 */
interface ILocationRecieved {
    /**
     * method is used for sending device current location
     * @param deviceLocation
     */
    fun sendDeviceCurrentLocation(deviceLocation: DeviceLocation)
}