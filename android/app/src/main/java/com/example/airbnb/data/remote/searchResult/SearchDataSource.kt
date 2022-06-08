package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodationResultDto

interface SearchDataSource {
    suspend fun getSearchResult(cityName:String): SearchAccommodationResultDto
}