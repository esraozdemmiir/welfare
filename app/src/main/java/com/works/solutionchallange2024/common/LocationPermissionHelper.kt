package com.works.solutionchallange2024.common

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.core.app.ActivityCompat

class LocationPermissionHelper : permissionS {

    override fun checktLocationPermission(context: Context, activity: Activity) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Konum Hatası")
            alertDialog.setMessage("Lütfen konum özelliğini açın.")
            alertDialog.setPositiveButton("Ayarlar") { _, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                activity.startActivity(intent)
            }
            alertDialog.show()
        }

        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(ACCESS_FINE_LOCATION), 100
            )
        }
    }
}