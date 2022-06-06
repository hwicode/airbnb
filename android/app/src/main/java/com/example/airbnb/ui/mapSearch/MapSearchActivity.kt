package com.example.airbnb.ui.mapSearch

import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import com.example.airbnb.BuildConfig
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityMainBinding
import com.example.airbnb.ui.common.TextDrawable
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MapSearchActivity : AppCompatActivity() {

    private lateinit var tMapView: TMapView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_search)

        tMapView = findViewById<TMapView>(R.id.tmap)
        tMapView.setSKTMapApiKey(BuildConfig.TMAP_KEY)
        val markItem = TMapMarkerItem()
        val markItem2 = TMapMarkerItem()
        val markItem3 = TMapMarkerItem()
        val tMapPoint1 = TMapPoint(37.570841, 126.985302)
        val tMapPoint2 = TMapPoint(37.570842, 126.995303)
        val tMapPoint3 = TMapPoint(37.580843, 126.985304)
        val textDrawable = TextDrawable("$23,000")
        val textDrawable2 = TextDrawable("$230,000")
        val textDrawable3 = TextDrawable("$2,300,000")
        textDrawable.draw(Canvas())
        textDrawable2.draw(Canvas())
        textDrawable3.draw(Canvas())

        markItem.icon = textDrawable.toBitmap(200, 100)
        markItem.tMapPoint = tMapPoint1
        markItem2.icon = textDrawable2.toBitmap(200, 100)
        markItem2.tMapPoint = tMapPoint2
        markItem3.icon = textDrawable3.toBitmap(200, 100)
        markItem3.tMapPoint = tMapPoint3
        tMapView.addMarkerItem("markItem2", markItem2)
        tMapView.addMarkerItem("markItem3", markItem3)
        findViewById<TMapView>(R.id.tmap).addMarkerItem("markItem1", markItem)
        findViewById<TMapView>(R.id.tmap).setCenterPoint(126.985302, 37.570841)

        val first = TMapPoint(37.570841, 126.985302)
        val second = TMapPoint(37.580843, 126.985304)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tMapPolyLine = TMapData().findPathData(first, second)
                tMapPolyLine.lineColor = Color.BLUE
                tMapPolyLine.lineWidth = 2f
                println("거리 ${tMapPolyLine.distance}")
                val test = TMapData().findPathDataAllType(TMapData.TMapPathType.CAR_PATH, first, second)
                tMapView.addTMapPolyLine("line1", tMapPolyLine)
                println(test.textContent)
                println(test.toString())
                println(test.documentURI)
                println(test.documentElement)
                println(test.nodeValue)
            } catch (e: Exception) {
                println(e)
                e.printStackTrace()
            }
        }


    }
}



