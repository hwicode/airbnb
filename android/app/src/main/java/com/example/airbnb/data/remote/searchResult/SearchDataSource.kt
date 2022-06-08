package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodationByConditionResultDto
import com.example.airbnb.data.dto.SearchAccommodationResultDto

interface SearchDataSource {
    suspend fun getSearchResultByTag(tag:String): SearchAccommodationResultDto
    suspend fun getSearchResultByAllCondition(searchCondition: Map<String,String>):SearchAccommodationByConditionResultDto
}