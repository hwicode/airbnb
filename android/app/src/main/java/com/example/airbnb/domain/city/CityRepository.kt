package com.example.airbnb.domain.city

import com.example.airbnb.domain.model.CityInfo

interface CityRepository {

    suspend fun getCityInfo(): List<CityInfo>

}