package com.app.nearbyapp.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.nearbyapp.models.Location
import com.google.android.gms.location.LocationServices

object LocationUtils {

    const val PERMISSION_REQUEST_CODE = 101
    var currentLocation: Location? = null
        private set

    private fun checkLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission(
        activity: Activity,
        callback: (granted: Boolean) -> Unit
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(
                activity,
                "We need location permission to show nearby places!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    fun fetchLocation(activity: Activity, callback: (latitude: Double, longitude: Double) -> Unit) {
        if (!checkLocationPermission(activity)) {
            requestLocationPermission(activity) { granted ->
                if (granted) {
                    fetchLocation(activity, callback)
                } else {
                    Toast.makeText(
                        activity,
                        "We need location permission to show nearby places!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            return
        }

        val locationClient = LocationServices.getFusedLocationProviderClient(activity)

        locationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                callback(location.latitude, location.longitude)
                currentLocation = Location(location.latitude, location.longitude)
            } else {
                Toast.makeText(
                    activity,
                    "Sorry, we couldn't fetch your location!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
