package com.example.airbnb.domain

import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResult.SearchResultAccommodation

interface SearchRepository {
    suspend fun getAccomnodationsByTag(tag:String, pageNum:Int):List<SearchResultAccommodation>
    suspend fun getAccommodationsByAllCondition(searchCondition: SearchCondition): List<SearchResultAccommodation>

}