package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.SearchAccommodationResultDto

class SearchRemoteDataSource(private val api:SearchResultApi):  SearchDataSource {
    override suspend fun getSearchResult(cityName:String): SearchAccommodationResultDto {
        return api.getAccommodationsByCity(cityName)
    }
}