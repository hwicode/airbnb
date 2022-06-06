package com.example.airbnb.ui.searchResult

import androidx.lifecycle.ViewModel
import com.example.airbnb.domain.model.SearchResultAccommodation

class SearchResultViewModel:ViewModel() {

    fun makeDummySearchResultList(): List<SearchResultAccommodation> {
        val accommodations = mutableListOf<SearchResultAccommodation>()
        for(i in 0..100){
            accommodations.add(SearchResultAccommodation("dummy Image", true,"서울 왕십리 게스트하우스"))
        }
        return accommodations
    }
}