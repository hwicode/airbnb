package com.example.airbnb.domain.city

import com.example.airbnb.data.city.CityDto

interface CityRepository {

    suspend fun getCityInfo(): CityDto

}