package com.example.airbnb.data.city

interface CityDataSource {

    suspend fun getCityInfo(): CityDto

}