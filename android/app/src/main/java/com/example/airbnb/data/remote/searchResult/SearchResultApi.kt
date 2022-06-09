package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SearchResultApi {

    @GET("accommodations/cities")
    suspend fun getAccommodationsByCity(@Query("cityName") cityName: String, @Query("page") pageNum:Int):SearchAccommodation


    @GET("accommodations/search")
    suspend fun getAccommodationsByAllCondition(@QueryMap condtion:Map<String,String>):SearchAccommodation


}