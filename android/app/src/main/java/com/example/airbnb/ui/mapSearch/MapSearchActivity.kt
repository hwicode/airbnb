package com.example.airbnb.ui.mapSearch

import android.content.Intent
import android.graphics.Canvas
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.BuildConfig
import com.example.airbnb.R
import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResult.SearchResultAccommodation
import com.example.airbnb.ui.accommodationDetail.AccommodationDetailActivity
import com.example.airbnb.ui.common.TextDrawable
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import org.koin.android.ext.android.inject


class MapSearchActivity : AppCompatActivity() {

    private lateinit var tMapView: TMapView
    private lateinit var cardRecyclerView: RecyclerView
    private var markerCount= 1
    private var page = 1
    private lateinit var geocoder:Geocoder
    private val viewModel: MapSearchViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_search)
        geocoder= Geocoder(this)
        val savedCondition = intent.getParcelableExtra<SearchCondition>("condition")

        println( geocoder.getFromLocationName("서울특별시 관악구 관악로11가길",10)[0].latitude)
        println(geocoder.getFromLocationName("서울특별시 관악구 관악로11가길",10)[0].longitude)
        savedCondition?.let { viewModel.loadSearchCondition(it) }
        tMapView = findViewById<TMapView>(R.id.tmap)
        cardRecyclerView = findViewById(R.id.rv_map_card_list)
        tMapView.setSKTMapApiKey(BuildConfig.TMAP_KEY)
        val adapter = MapSearchAccommodationCardAdapter() {id->
            openDetail(id)
        }
        cardRecyclerView.adapter = adapter
        viewModel.searchCondition.observe(this) {
            if (viewModel.validateSearchCondition()) {
                viewModel.getSearchResultByAllCondition(1)
            } else {
                viewModel.getSearchResultByTag(page)

            }
        }
        val center = viewModel.searchCondition.value?.searchTag
        val latitude = geocoder.getFromLocationName(center, 10)[0].latitude
        val longitude= geocoder.getFromLocationName(center, 10)[0].longitude
        tMapView.setCenterPoint(longitude, latitude)
        viewModel.searchResult.observe(this) {
            val result = it.map { searchResult ->
                searchResult as SearchResultAccommodation
            }
            adapter.submitList(result)
            result.map { accommodation->
                getLocation(accommodation.address, accommodation.payPerNight)
            }


        }
    }

    private fun getLocation(address:String, pay:Int){
        val latitude = geocoder.getFromLocationName(address, 10)[0].latitude
        val longitude= geocoder.getFromLocationName(address, 10)[0].longitude
        addTMapMarker(latitude, longitude, pay)
    }

    private fun addTMapMarker(latitude:Double, longitude:Double, price:Int) {
        val markItem = TMapMarkerItem()
        val tMapPoint1 = TMapPoint(latitude, longitude)
        val textDrawable = TextDrawable("$price")
        textDrawable.draw(Canvas())
        markItem.icon = textDrawable.toBitmap(200, 100)
        markItem.tMapPoint = tMapPoint1
        tMapView.addMarkerItem("markItem${markerCount++}", markItem)

    }

    private fun openDetail(accommodationId: Int) {

        val intent = Intent(this, AccommodationDetailActivity::class.java)
        intent.putExtra("id",accommodationId)
        startActivity(intent)
    }

}



