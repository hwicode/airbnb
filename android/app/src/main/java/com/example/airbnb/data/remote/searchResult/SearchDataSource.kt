package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.*

interface SearchDataSource {
    suspend fun getSearchResultByTag(tag:String, pageNum:Int): SearchAccommodation
    suspend fun getSearchResultByAllCondition(searchCondition: Map<String,String>): SearchAccommodation
    suspend fun getWishList():WishDetailDto
    suspend fun getReservationList(): ReservationDto
}