package com.example.airbnb.domain.city

import com.example.airbnb.data.remote.city.CityDataSource
import com.example.airbnb.domain.model.CityInfo
import com.example.airbnb.data.dto.toCityInfo

class CityRepositoryImpl(private val dataSource: CityDataSource) : CityRepository {

    override suspend fun getCityInfo(): List<CityInfo> {
        return dataSource.getCityInfo().map {
            it.toCityInfo()
        }
    }
}