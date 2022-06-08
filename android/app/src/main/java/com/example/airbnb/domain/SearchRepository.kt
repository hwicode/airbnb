package com.example.airbnb.domain

import com.example.airbnb.domain.model.SearchResultAccommodation

interface SearchRepository {
    suspend fun getAccomnodations(cityName:String):List<SearchResultAccommodation>
}