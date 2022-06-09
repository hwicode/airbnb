package com.example.airbnb.data.remote.city

import com.example.airbnb.data.dto.CityDto

interface CityDataSource {

    suspend fun getCityInfo(): CityDto

}