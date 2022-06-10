package com.example.airbnb.data.repository

import com.example.airbnb.data.dto.toSearchResultAccommodation
import com.example.airbnb.data.remote.searchResult.SearchDataSource
import com.example.airbnb.domain.SearchRepository
import com.example.airbnb.domain.model.SearchCondition
import com.example.airbnb.domain.model.SearchResult
import com.example.airbnb.domain.model.toQueryMap

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource): SearchRepository {

    override suspend fun getAccomnodationsByTag(tag: String, pageNum:Int): List<SearchResult.SearchResultAccommodation> {
        return searchDataSource.getSearchResultByTag(tag, pageNum).content.map{
            it.toSearchResultAccommodation()
        }
    }

    override suspend fun getAccommodationsByAllCondition(searchCondition: SearchCondition): List<SearchResult.SearchResultAccommodation> {
       return searchDataSource.getSearchResultByAllCondition(searchCondition.searchTag, searchCondition.toQueryMap()).content.map {
           it.toSearchResultAccommodation()
       }
    }

}