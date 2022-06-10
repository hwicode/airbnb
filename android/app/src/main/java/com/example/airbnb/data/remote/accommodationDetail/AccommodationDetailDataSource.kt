package com.example.airbnb.data.remote.accommodationDetail

import com.example.airbnb.data.dto.AccommodationDetailDto

interface AccommodationDetailDataSource {

    suspend fun getAccommodationDetail(accommodationId: Int): AccommodationDetailDto

}