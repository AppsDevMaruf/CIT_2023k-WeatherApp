package com.maruf.weatherappcit2023
import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.maruf.weatherappcit2023.viewmodels.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : AppCompatActivity() {

    private val locationViewModel: LocationViewModel by viewModels()
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                detectUserLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                detectUserLocation()
            } else -> {
            // No location access granted.
        }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }
    @SuppressLint("MissingPermission", "VisibleForTests")
    private fun detectUserLocation() {
        val provider = FusedLocationProviderClient(this)
        provider.lastLocation.addOnSuccessListener {
            it?.let {
                locationViewModel.setNewLocations(it)
            }

            /*Toast.makeText(this,"${location.latitude},${location.longitude}",Toast.LENGTH_LONG).show()*/
        }
    }

}