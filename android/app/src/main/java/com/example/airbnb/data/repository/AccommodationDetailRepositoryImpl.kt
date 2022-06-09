package com.example.airbnb.data.repository

import com.example.airbnb.data.dto.toAccommodationDetailItem
import com.example.airbnb.data.remote.accommodationDetail.AccommodationDetailDataSource
import com.example.airbnb.domain.AccommodationDetailRepository
import com.example.airbnb.domain.model.AccommodationDetailItem

class AccommodationDetailRepositoryImpl(private val accommodationDetailDataSource: AccommodationDetailDataSource) : AccommodationDetailRepository {

    override suspend fun getAccommodationDetail(accommodationId: Int): AccommodationDetailItem {
        return accommodationDetailDataSource.getAccommodationDetail(accommodationId).toAccommodationDetailItem()
    }
}