package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodationResultDto
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchResultApi {

    @GET("accommodations/cities")
    suspend fun getAccommodationsByCity(@Query("city_name") cityName: String):SearchAccommodationResultDto
}