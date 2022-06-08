package com.example.airbnb.data.city

class CityRemoteDataSource(private val api: CityApi) : CityDataSource {

    override suspend fun getCityInfo(): CityDto {
        return api.getCityInfo()
    }
}