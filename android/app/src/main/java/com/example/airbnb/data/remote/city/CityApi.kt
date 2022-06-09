package com.example.airbnb.data.remote.city

import com.example.airbnb.data.dto.CityDto
import retrofit2.http.GET

interface CityApi {

    @GET("cities")
    suspend fun getCityInfo(): CityDto

}