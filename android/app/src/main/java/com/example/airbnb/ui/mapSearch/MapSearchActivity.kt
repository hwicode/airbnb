package com.example.airbnb.ui.mapSearch

import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.BuildConfig
import com.example.airbnb.R
import com.example.airbnb.domain.model.SearchResultAccommodation
import com.example.airbnb.ui.common.TextDrawable
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView


class MapSearchActivity : AppCompatActivity() {

    private lateinit var tMapView: TMapView
    private lateinit var cardRecyclerView:RecyclerView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_search)
        tMapView = findViewById<TMapView>(R.id.tmap)
        tMapMarkerTest()
        cardRecyclerView= findViewById(R.id.rv_map_card_list)
        tMapView.setSKTMapApiKey(BuildConfig.TMAP_KEY)
        val adapter= MapSearchAccommodationCardAdapter(){
            openDetail()
        }
        cardRecyclerView.adapter= adapter
        adapter.submitList(getSearchResultAccommodations())

    }

    private fun tMapMarkerTest(){
        val markItem = TMapMarkerItem()
        val tMapPoint1 = TMapPoint(37.570841, 126.985302)
        val textDrawable = TextDrawable("$23,000")
        textDrawable.draw(Canvas())
        markItem.icon = textDrawable.toBitmap(200, 100)
        markItem.tMapPoint = tMapPoint1
        tMapView.addMarkerItem("markItem1", markItem)
        tMapView.setCenterPoint(126.985302, 37.570841)
    }

    // SearchResultFragment -> MapSearchActivity로 이동할때 전달받는다 가정
    private fun getSearchResultAccommodations():List<SearchResultAccommodation>{
        val accommodations = mutableListOf<SearchResultAccommodation>()
        for(i in 0..100){
            accommodations.add(SearchResultAccommodation("dummy Image", true,"서울 왕십리 게스트하우스"))
        }
        return accommodations
    }

    private fun openDetail(){
        //navigate to detail
    }
}



