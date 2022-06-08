package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodationByConditionResultDto
import com.example.airbnb.data.dto.SearchAccommodationResultDto

class SearchRemoteDataSource(private val api:SearchResultApi):  SearchDataSource {
    override suspend fun getSearchResultByTag(tag:String): SearchAccommodationResultDto {
        return api.getAccommodationsByCity(tag)
    }

    override suspend fun getSearchResultByAllCondition(searchCondition: Map<String,String>):SearchAccommodationByConditionResultDto {
        return api.getAccommodationsByAllCondition(searchCondition)
    }
}