package com.example.airbnb.domain

import com.example.airbnb.domain.model.AccommodationDetailItem

interface AccommodationDetailRepository {

    suspend fun getAccommodationDetail(accommodationId: Int): AccommodationDetailItem

}