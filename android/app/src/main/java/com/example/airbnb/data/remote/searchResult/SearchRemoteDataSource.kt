package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.*

class SearchRemoteDataSource(private val api:SearchResultApi):  SearchDataSource {
    override suspend fun getSearchResultByTag(tag:String, pageNum:Int): SearchAccommodation {
        return api.getAccommodationsByCity(tag, pageNum)
    }

    override suspend fun getSearchResultByAllCondition(searchCondition: Map<String,String>):SearchAccommodation {
        return api.getAccommodationsByAllCondition(searchCondition)
    }
}