package com.example.airbnb.domain.city

import com.example.airbnb.data.city.CityDataSource
import com.example.airbnb.data.city.CityInfo
import com.example.airbnb.data.city.toCityInfo

class CityRepositoryImpl(private val dataSource: CityDataSource) : CityRepository {

    override suspend fun getCityInfo(): List<CityInfo> {
        return dataSource.getCityInfo().map {
            it.toCityInfo()
        }
    }
}