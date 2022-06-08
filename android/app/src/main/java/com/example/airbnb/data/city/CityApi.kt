package com.example.airbnb.data.city

import retrofit2.http.GET

interface CityApi {

    @GET("cities")
    suspend fun getCityInfo(): CityDto

}