package com.example.airbnb.domain

import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResultAccommodation

interface SearchRepository {
    suspend fun getAccomnodationsByTag(tag:String):List<SearchResultAccommodation>
    suspend fun getAccommodationsByAllCondition(searchCondition: SearchCondition): List<SearchResultAccommodation>
}