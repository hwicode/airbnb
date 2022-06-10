package com.example.airbnb.data.remote.accommodationDetail

import com.example.airbnb.data.dto.AccommodationDetailDto

class AccommodationDetailRemoteDataSource(private val api: AccommodationDetailApi) : AccommodationDetailDataSource {

    override suspend fun getAccommodationDetail(accommodationId: Int): AccommodationDetailDto {
        return api.getAccommodationDetail(accommodationId)
    }
}