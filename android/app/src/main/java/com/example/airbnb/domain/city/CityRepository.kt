package com.example.airbnb.domain.city

import com.example.airbnb.data.city.CityInfo

interface CityRepository {

    suspend fun getCityInfo(): List<CityInfo>

}