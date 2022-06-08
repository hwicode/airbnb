package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodationByConditionResultDto
import com.example.airbnb.data.dto.SearchAccommodationResultDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SearchResultApi {

    @GET("accommodations/cities")
    suspend fun getAccommodationsByCity(@Query("city_name") cityName: String):SearchAccommodationResultDto


    @GET("accommodations/search")
    suspend fun getAccommodationsByAllCondition(@QueryMap condtion:Map<String,String>):SearchAccommodationByConditionResultDto
}