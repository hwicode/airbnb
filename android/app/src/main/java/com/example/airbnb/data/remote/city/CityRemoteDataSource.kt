package com.example.airbnb.data.remote.city

import com.example.airbnb.data.dto.CityDto

class CityRemoteDataSource(private val api: CityApi) : CityDataSource {

    override suspend fun getCityInfo(): CityDto {
        return api.getCityInfo()
    }
}