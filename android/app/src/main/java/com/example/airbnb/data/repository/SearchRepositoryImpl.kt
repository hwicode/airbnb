package com.example.airbnb.data.repository

import com.example.airbnb.data.dto.toSearchResultAccommodation
import com.example.airbnb.data.remote.searchResult.SearchDataSource
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.SearchResultAccommodation

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource): SearchRepository {
    override suspend fun getAccomnodations(cityName:String): List<SearchResultAccommodation> {
        return searchDataSource.getSearchResult(cityName).map {
            it.toSearchResultAccommodation()
        }
    }
}