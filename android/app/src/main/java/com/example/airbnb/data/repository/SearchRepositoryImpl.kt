package com.example.airbnb.data.repository

import com.example.airbnb.data.dto.toReservationItem
import com.example.airbnb.data.dto.toSearchResultAccommodation
import com.example.airbnb.data.dto.toWishItem
import com.example.airbnb.data.remote.searchResult.SearchDataSource
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.*

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource): SearchRepository {

    override suspend fun getAccomnodationsByTag(tag: String, pageNum:Int): List<SearchResultAccommodation> {
        return searchDataSource.getSearchResultByTag(tag, pageNum).content.map{
            it.toSearchResultAccommodation()
        }
    }

    override suspend fun getAccommodationsByAllCondition(searchCondition: SearchCondition): List<SearchResultAccommodation> {
       return searchDataSource.getSearchResultByAllCondition(searchCondition.toQueryMap()).content.map {
           it.toSearchResultAccommodation()
       }
    }

}