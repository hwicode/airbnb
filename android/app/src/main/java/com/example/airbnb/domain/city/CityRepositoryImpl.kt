package com.example.airbnb.domain.city

import com.example.airbnb.data.city.CityDataSource
import com.example.airbnb.data.city.CityDto

class CityRepositoryImpl(private val dataSource: CityDataSource) : CityRepository {

    override suspend fun getCityInfo(): CityDto {
        return dataSource.getCityInfo()
    }
}