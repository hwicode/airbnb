package com.example.airbnb.domain

import com.example.airbnb.domain.model.ReservationItem
import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResultAccommodation
import com.example.airbnb.domain.model.WishItem

interface SearchRepository {
    suspend fun getAccomnodationsByTag(tag:String):List<SearchResultAccommodation>
    suspend fun getAccommodationsByAllCondition(searchCondition: SearchCondition): List<SearchResultAccommodation>
    suspend fun getWishList():List<WishItem>
    suspend fun getReservationList():List<ReservationItem>
}