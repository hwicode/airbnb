package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.ReservationDto
import com.example.airbnb.data.dto.SearchAccommodationByConditionResultDto
import com.example.airbnb.data.dto.SearchAccommodationResultDto
import com.example.airbnb.data.dto.WishDetailDto

class SearchRemoteDataSource(private val api:SearchResultApi):  SearchDataSource {
    override suspend fun getSearchResultByTag(tag:String): SearchAccommodationResultDto {
        return api.getAccommodationsByCity(tag)
    }

    override suspend fun getSearchResultByAllCondition(searchCondition: Map<String,String>):SearchAccommodationByConditionResultDto {
        return api.getAccommodationsByAllCondition(searchCondition)
    }

    override suspend fun getWishList(): WishDetailDto {
        return api.getWishList()
    }

    override suspend fun getReservationList(): ReservationDto {
        return api.getReservationList()
    }
}