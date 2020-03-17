package com.weather.forecast.model.location

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.weather.forecast.utils.CONNECTION_FAILURE_RESOLUTION_REQUEST
import com.weather.forecast.utils.Logger

class FusedLocationServiceProvider(
    private val context: Context,
    private val locationReceived: ILocationRecieved
) : GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var locationRequest: LocationRequest

    fun configure() {
        googleApiClient = GoogleApiClient.Builder(context)
            // The next two lines tell the new client that “this” current class will handle connection stuff
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            //fourth line adds the LocationServices API endpoint from GooglePlayServices
            .addApi(LocationServices.API)
            .build()

        // Create the LocationRequest object
        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval((10 * 1000).toLong())        // 10 seconds, in milliseconds
            .setFastestInterval((1 * 1000).toLong()) // 1 second, in milliseconds
    }

    fun connect() {
        //Now lets connect to the API
        if (::googleApiClient.isInitialized)
            googleApiClient.connect()
    }

    fun disconnect() {
        //Disconnect from API onPause()
        if (::googleApiClient.isInitialized && googleApiClient.isConnected) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
            googleApiClient.disconnect()
        }
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                    context as Activity,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST
                )
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (e: IntentSender.SendIntentException) {
                Logger.error(
                    FusedLocationServiceProvider::class.simpleName,
                    "Google Play services error ",
                    e
                )
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */

            Logger.error(
                FusedLocationServiceProvider::class.simpleName,
                "Location services connection failed with code ",
                connectionResult.errorCode
            )
        }
    }

    override fun onLocationChanged(location: Location?) {
        locationReceived.sendDeviceCurrentLocation(
            DeviceLocation(
                location?.latitude,
                location?.longitude
            )
        )
    }

    override fun onConnected(bundle: Bundle?) {
        val location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient,
                locationRequest,
                this
            )
        } else {
            //If everything went fine lets get latitude and longitude
            locationReceived.sendDeviceCurrentLocation(
                DeviceLocation(
                    location.latitude,
                    location.longitude
                )
            )
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented")
    }
}