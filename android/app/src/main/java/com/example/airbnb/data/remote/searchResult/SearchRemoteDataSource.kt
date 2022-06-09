package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodation

class SearchRemoteDataSource(private val api: SearchResultApi) : SearchDataSource {
    override suspend fun getSearchResultByTag(tag: String, pageNum: Int): SearchAccommodation {
        return api.getAccommodationsByCity(tag, pageNum)
    }

    override suspend fun getSearchResultByAllCondition(tag:String, queryMap: Map<String, String>): SearchAccommodation {
        return api.getAccommodationsByAllCondition(tag, queryMap)
    }

}