package com.example.airbnb.data.remote.accommodationDetail

import com.example.airbnb.data.dto.AccommodationDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AccommodationDetailApi {

    @GET("accommodations/{id}")
    suspend fun getAccommodationDetail(@Path("id") accommodationId: Int): AccommodationDetailDto

}