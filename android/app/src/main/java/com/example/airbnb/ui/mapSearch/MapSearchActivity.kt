package com.example.airbnb.ui.mapSearch

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.airbnb.BuildConfig
import com.example.airbnb.R
import com.example.airbnb.ui.common.TextDrawable
import com.google.android.material.snackbar.Snackbar
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView


class MapSearchActivity : AppCompatActivity() {
    private lateinit var tMapView:TMapView
    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,  // 도시 블록 단위
            Manifest.permission.ACCESS_FINE_LOCATION,  // 더 정밀한 단위
            Manifest.permission.ACCESS_NETWORK_STATE
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_search)

        if (isAllPermissionsGranted()) {
            tMapView= findViewById<TMapView>(R.id.tmap)
            tMapView.setSKTMapApiKey(BuildConfig.TMAP_KEY)
            val markItem= TMapMarkerItem()
            val markItem2= TMapMarkerItem()
            val markItem3= TMapMarkerItem()
            val tMapPoint1 = TMapPoint(37.570841, 126.985302)
            val tMapPoint2=  TMapPoint(37.570842, 126.995303)
            val tMapPoint3=  TMapPoint(37.580843, 126.985304)
            val textDrawable= TextDrawable("$23,000")
            val textDrawable2= TextDrawable("$230,000")
            val textDrawable3= TextDrawable("$2,300,000")
            textDrawable.draw(Canvas())
            textDrawable2.draw(Canvas())
            textDrawable3.draw(Canvas())

            markItem.icon= textDrawable.toBitmap(200,100)
            markItem.tMapPoint= tMapPoint1
            markItem2.icon= textDrawable2.toBitmap(200,100)
            markItem2.tMapPoint =tMapPoint2
            markItem3.icon= textDrawable3.toBitmap(200,100)
            markItem3.tMapPoint = tMapPoint3
            tMapView.addMarkerItem("markItem2", markItem2)
            tMapView.addMarkerItem("markItem3", markItem3)
            findViewById<TMapView>(R.id.tmap).addMarkerItem("markItem1",markItem)
            findViewById<TMapView>(R.id.tmap).setCenterPoint( 126.985302, 37.570841 )

        } else {
            requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
        }
    }

    private fun isAllPermissionsGranted(): Boolean = REQUIRED_PERMISSIONS.all { permission ->
        ContextCompat.checkSelfPermission(this, permission) ==
                PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach { permission ->
                when {
                    permission.value -> {
                        Snackbar.make(tMapView, "Permission granted", Snackbar.LENGTH_SHORT).show()
                    }
                    shouldShowRequestPermissionRationale(permission.key) -> {
                        //Setting 페이지 이동
                    }
                    else->{
                        Snackbar.make(tMapView, "Permission denied", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
}